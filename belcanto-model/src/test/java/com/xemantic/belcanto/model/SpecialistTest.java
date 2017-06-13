package com.xemantic.belcanto.model;

/**
 * Test of the {@link Specialist}.
 *
 * @author morisil
 */
public class SpecialistTest extends PersonTest {

  @Override
  protected Person newPerson() {
    return new Specialist();
  }

}
