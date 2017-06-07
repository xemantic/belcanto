package com.xemantic.belcanto.service;

import com.xemantic.belcanto.model.Customer;
import com.xemantic.belcanto.service.repository.CustomerRepository;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author morisil
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerResourceIntegrationTest {

  @ClassRule
  public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

  @Rule
  public final SpringMethodRule springMethodRule = new SpringMethodRule();

  @Inject
  private CustomerRepository repository;

  @LocalServerPort
  private int port;

  @Test
  public void createCustomer_validCustomer_shouldWriteCustomerInDb() {
    given()
        .port(port)
        .contentType("application/json")
        .body("{\"name\": \"John\", \"surname\": \"Smith\"}")
    .when()
        .post("/customers")
    .then()
        .statusCode(201); // created

    // then
    assertThat(repository.count(), is(1L));
    Customer customer = repository.findAll().iterator().next();
    assertThat(customer.getName(), is("John"));
    assertThat(customer.getSurname(), is("Smith"));
  }

}
