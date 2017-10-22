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

package com.xemantic.belcanto.service.test

import com.xemantic.belcanto.model.Customer
import com.xemantic.belcanto.service.repository.CustomerRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

import javax.inject.Inject

import static BelcantoTests.jsonEquals
import static io.restassured.RestAssured.given
import static org.assertj.core.api.Assertions.assertThat

/**
 * Test of the {@code /customers} endpoint.
 *
 * @author morisil
 */
@RunWith(SpringRunner)
@BelcantoTest
class CustomerRestResourceTest {

  @Inject @Rule public BelcantoTestContext context

  @Inject CustomerRepository customerRepository

  @Test
  void createCustomer_validCustomerDataSupplied_shouldWriteCustomerInDb() {
    String customerLink = given()
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
    assertThat(customerRepository.findById(customerId))
        .isPresent()
        .hasValueSatisfying { customer ->
            assertThat(customer.name).isEqualTo('John')
            assertThat(customer.surname).isEqualTo('Smith')
            assertThat(customer.birthDate).isEqualTo('1981-12-18')
        }
  }

  @Test
  void createCustomer_withEmbeddedAddress_shouldWriteCustomerAndAddressInDb() {
    String customerLink = given()
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
    assertThat(customerRepository.findById(customerId))
        .isPresent()
        .hasValueSatisfying { customer ->
            assertThat(customer.name).isEqualTo('John')
            assertThat(customer.surname).isEqualTo('Smith')
            assertThat(customer.addresses)
                .hasSize(1)
                .hasOnlyOneElementSatisfying { address ->
                    assertThat(address.street).isEqualTo('Somewhere')
                }
        }
  }

  @Test
  void readCustomer_nonExistentId_shouldReturn404() {
    // given
    long nonExistentId = 42L
    given()

        .when()
        .get("/customers/${nonExistentId}")

        .then()
        .statusCode(404) // not found
  }

  @Test
  void readCustomer_existentId_shouldReturnCustomerData() {
    // given
    // db state
    def customer = context.populate(
        new Customer( // convenient way of populating DB state with Groovy
            name: 'John',
            surname: 'Smith'
        )
    )
    given()

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
              "href" : "http://localhost:${context.port}/customers/${customer.id}"
            },
            "customer" : {
              "href" : "http://localhost:${context.port}/customers/${customer.id}"
            },
            "specialist" : {
              "href" : "http://localhost:${context.port}/customers/${customer.id}/specialist"
            }
          }
        }
        """))
  }

}
