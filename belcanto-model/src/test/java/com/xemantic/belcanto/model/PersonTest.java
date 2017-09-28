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

package com.xemantic.belcanto.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
    assertThat(displayName).isEqualTo("John Smith");
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
    assertThat(address).isNotPresent();
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
    assertThat(result).isPresent().hasValueSatisfying(a ->
        assertThat(a.getStreet()).isEqualTo("foo")
    );
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
    assertThat(result).isPresent().hasValueSatisfying(a ->
        assertThat(a.getStreet()).isEqualTo("bar")
    );
  }

  protected abstract Person newPerson();

}
