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

import com.xemantic.belcanto.model.Specialist
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

import javax.inject.Inject
import java.time.LocalDate

import static BelcantoTests.jsonEquals
import static io.restassured.RestAssured.given

/**
 * Test of API use cases.
 *
 * @author morisil
 */
@RunWith(SpringRunner)
@BelcantoTest
class ApiUseCasesApiContractTest {

  @Inject @Rule public BelcantoTestContext context

  @Test
  void createCustomer_forExistingSpecialist_shouldAssociateNewCustomerWithTheSpecialist() {
    // given
    // db state
    def specialist = context.populate(
        new Specialist( // convenient way of populating DB state with Groovy
            name: 'Max',
            surname: 'Mustermann',
            birthDate: LocalDate.parse('1970-12-18')
        )
    )

    // create customer
    String customerLink = given()
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
                  "href" : "http://localhost:${context.port}/customers/${customerId}"
                },
                "customer" : {
                  "href" : "http://localhost:${context.port}/customers/${customerId}"
                },
                "specialist" : {
                  "href" : "http://localhost:${context.port}/customers/${customerId}/specialist"
                }
              }
            } ]
          },
          "_links" : {
            "self" : {
              "href" : "http://localhost:${context.port}/specialists/${specialist.id}/customers"
            }
          }
        }
        """))
  }

}
