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

import com.xemantic.belcanto.model.Specialist
import com.xemantic.belcanto.service.repository.SpecialistRepository
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.rules.SpringClassRule
import org.springframework.test.context.junit4.rules.SpringMethodRule

import javax.inject.Inject
import java.time.LocalDate

import static io.restassured.RestAssured.given
import static com.xemantic.belcanto.service.BelcantoTests.jsonEquals

/**
 * Integration test containing main use cases.
 *
 * @author morisil
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UseCasesTest {

  @ClassRule
  public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule()

  @Rule
  public final SpringMethodRule springMethodRule = new SpringMethodRule()

  @Inject
  SpecialistRepository specialistRepository

  @LocalServerPort
  int port

  @Test
  void createCustomer_forExistingSpecialist_shouldAssociateNewCustomerWithTheSpecialist() {
    // given
    // db state
    def specialist = specialistRepository.save(
        new Specialist( // convenient way of populating DB state with Groovy
            name: 'Max',
            surname: 'Mustermann',
            birthDate: LocalDate.parse('1970-12-18')
        )
    )

    // create customer
    String customerLink = given()
        .port(port)
        .contentType('application/json')
        .body('''
        {
          "name": "John",
          "surname": "Smith",
          "birthDate": "1976-11-23"
        }
        ''')
    .when()
        .post('/customers')
    .then()
        .statusCode(201) // created
    .extract()
        .path('_links.self.href')

    // associate customer with specialist
    given()
        .port(port)
        .contentType('text/uri-list')
        .body(customerLink)
    .when()
        .post("/specialists/${specialist.id}/customers")
    .then()
        .statusCode(204) // saved

    // then
    // final assertion
    Long customerId = BelcantoTests.extractId(customerLink)

    given()
        .port(port)
    .when()
        .get("/specialists/${specialist.id}/customers")
    .then()
        .statusCode(200)
        .content(jsonEquals("""
        {"_embedded" : {
            "customers" : [ {
              "name" : "John",
              "surname" : "Smith",
              "birthDate" : "1976-11-23",
              "addresses" : [ ],
              "appointments": [ ],
              "displayName" : "John Smith",
              "_links" : {
                "self" : {
                  "href" : "http://localhost:${port}/customers/${customerId}"
                },
                "customer" : {
                  "href" : "http://localhost:${port}/customers/${customerId}"
                },
                "specialist" : {
                  "href" : "http://localhost:${port}/customers/${customerId}/specialist"
                }
              }
            } ]
          },
          "_links" : {
            "self" : {
              "href" : "http://localhost:${port}/specialists/${specialist.id}/customers"
            }
          }
        }
        """))
  }

}
