package com.xemantic.belcanto.model;

/**
 * Test of the {@link Customer} entity.
 *
 * @author morisil
 */
public class CustomerTest extends PersonTest {

  @Override
  protected Person newPerson() {
    return new Customer();
  }

}
