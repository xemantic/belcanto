package com.xemantic.belcanto.model;

/**
 * Test of the {@link Audiologist}.
 *
 * @author morisil
 */
public class AudiologistTest extends PersonTest {

  @Override
  protected Person getPerson(String name, String surname) {
    Audiologist audiologist = new Audiologist();
    audiologist.setName(name);
    audiologist.setSurname(surname);
    return audiologist;
  }

}
