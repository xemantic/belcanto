package com.xemantic.belcanto.model;

/**
 * Test of the {@link Customer} entity.
 *
 * @author morisil
 */
public class CustomerTest extends PersonTest {

  @Override
  protected Person getPerson(String name, String surname) {
    Customer customer = new Customer();
    customer.setName(name);
    customer.setSurname(surname);
    return customer;
  }

}
