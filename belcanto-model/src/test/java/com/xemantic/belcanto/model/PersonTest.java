package com.xemantic.belcanto.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Abstract test of the {@link Person} entity.
 * <p>
 *   Note: implementing classes should override the {@link #newPerson()} factory
 *   method to provide class instance.
 * </p>
 * @author morisil
 */
public abstract class PersonTest {

  @Test
  public void getDisplayName_nameAndSurnameProvided_shouldReturnNameAndSurname() {
    // given
    Person person = newPerson();
    person.setName("John");
    person.setSurname("Smith");

    // when
    String displayName = person.getDisplayName();

    // then
    assertThat(displayName, is("John Smith"));
  }

  @Test
  public void getPrimaryAddress_noAddressProvided_shouldReturnEmptyOptional() {
    // given
    Person person = newPerson();
    person.setName("John");
    person.setSurname("Smith");
    person.setAddresses(null);

    // when
    Optional<Address> address = person.getPrimaryAddress();

    // then
    assertThat(address.isPresent(), is(false));
  }

  @Test
  public void getPrimaryAddress_1AddressProvided_shouldReturnAddress() {
    // given
    Person person = newPerson();
    person.setName("John");
    person.setSurname("Smith");
    Address address = new Address();
    address.setStreet("foo");
    address.setPrimary(true);
    person.setAddresses(Collections.singleton(address));

    // when
    Optional<Address> result = person.getPrimaryAddress();

    // then
    assertThat(result.get().getStreet(), is("foo"));
  }

  @Test
  public void getPrimaryAddress_2AddressesProvided2ndIsPrimary_shouldReturnPrimaryAddress() {
    // given
    Person person = newPerson();
    person.setName("John");
    person.setSurname("Smith");
    Address address1 = new Address();
    address1.setStreet("foo");
    address1.setPrimary(false);
    Address address2 = new Address();
    address2.setStreet("bar");
    address2.setPrimary(true);
    person.setAddresses(new HashSet<>(Arrays.asList(address1, address2)));

    // when
    Optional<Address> result = person.getPrimaryAddress();

    // then
    assertThat(result.get().getStreet(), is("bar"));
  }

  protected abstract Person newPerson();

}
