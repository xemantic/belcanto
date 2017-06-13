/*
 * belcanto - mini CRM system linking specialist with their customers.
 * This project explores possibilities of building Model Driven Applications
 * on top of spring-boot.
 *
 * Copyright (C) 2017  Kazimierz Pogoda
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.xemantic.belcanto.service

import com.xemantic.belcanto.model.Customer
import com.xemantic.belcanto.service.repository.CustomerRepository
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.rules.SpringClassRule
import org.springframework.test.context.junit4.rules.SpringMethodRule

import javax.inject.Inject
import java.time.LocalDate

import static io.restassured.RestAssured.given
import static com.xemantic.belcanto.service.BelcantoTests.jsonEquals
import static org.hamcrest.Matchers.empty
import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.not
import static org.junit.Assert.assertThat

/**
 * Test of the {@code /customers} endpoint.
 *
 * @author morisil
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerRestResourceTest {

  @ClassRule
  public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule()

  @Rule
  public final SpringMethodRule springMethodRule = new SpringMethodRule()

  @Inject
  CustomerRepository customerRepository

  @LocalServerPort
  int port


  @Test
  void createCustomer_validCustomerDataSupplied_shouldWriteCustomerInDb() {
    String customerLink = given()
        .port(port)
        .contentType('application/json')
        .body('''
        {
          "name": "John",
          "surname": "Smith",
          "birthDate": "1981-12-18T00:00"
        }
        ''')
    .when()
        .post('/customers')
    .then()
        .statusCode(201) // created
    .extract()
        .path('_links.self.href')

    // then
    Long customerId = BelcantoTests.extractId(customerLink)
    Customer customer = customerRepository.findOne(customerId)
    assertThat(customer.getName(), is('John'))
    assertThat(customer.getSurname(), is('Smith'))
    assertThat(customer.getBirthDate(), is(LocalDate.parse('1981-12-18')))
  }

  @Test
  void createCustomer_withEmbeddedAddress_shouldWriteCustomerAndAddressInDb() {
    String customerLink = given()
        .port(port)
        .contentType('application/json')
        .body('''
        {
          "name": "John",
          "surname": "Smith",
          "addresses": [
            {
              "street": "Somewhere"
            }            
          ]
        }
        ''')
    .when()
        .post('/customers')
    .then()
        .statusCode(201) // created
    .extract()
        .path('_links.self.href')

    // then
    Long customerId = BelcantoTests.extractId(customerLink)
    Customer customer = customerRepository.findOne(customerId)
    assertThat(customer.getName(), is('John'))
    assertThat(customer.getSurname(), is('Smith'))
    def addresses = customer.getAddresses()
    assertThat(addresses, not(empty()))
    assertThat(addresses.iterator().next().getStreet(), is('Somewhere'))
  }

  @Test
  void readCustomer_nonExistentId_shouldReturn404() {
    // given
    long nonExistentId = 42L
    given()
        .port(port)
    .when()
        .get("/customers/${nonExistentId}")
    .then()
        .statusCode(404) // not found
  }

  @Test
  void readCustomer_existentId_shouldReturnCustomerData() {
    // given
    // db state
    def customer = customerRepository.save(
        new Customer( // convenient way of populating DB state with Groovy
            name: 'John',
            surname: 'Smith'
        )
    )
    given()
        .port(port)
    .when()
        .get("/customers/${customer.id}")
    .then()
        .statusCode(200)
        .content(jsonEquals("""
        {
          "name" : "John",
          "surname" : "Smith",
          "addresses" : [ ],
          "appointments" : [ ],
          "birthDate" : null,
          "displayName" : "John Smith",
          "_links" : {
            "self" : {
              "href" : "http://localhost:${port}/customers/${customer.id}"
            },
            "customer" : {
              "href" : "http://localhost:${port}/customers/${customer.id}"
            },
            "specialist" : {
              "href" : "http://localhost:${port}/customers/${customer.id}/specialist"
            }
          }
        }
        """))
  }

}
