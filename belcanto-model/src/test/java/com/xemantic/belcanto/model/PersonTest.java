package com.xemantic.belcanto.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Abstract test of the {@link Person} entity.
 * <p>
 *   Note: implementing classes should override the {@link #getPerson(String, String)}
 *   method.
 * </p>
 * @author morisil
 */
public abstract class PersonTest {

  @Test
  public void getDisplayName_nameAndSurnameProvided_shouldReturnNameAndSurname() {
    // given
    String name = "John";
    String surname = "Smith";
    Person person = getPerson(name, surname);

    // when
    String displayName = person.getDisplayName();

    // then
    assertThat(displayName, is("John Smith"));
  }

  protected abstract Person getPerson(String name, String surname);

}
