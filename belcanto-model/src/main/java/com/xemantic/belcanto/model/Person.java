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

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

/**
 * Represents abstract person.
 *
 * @author morisil
 */
@MappedSuperclass
public abstract class Person implements Party {

  @Id
  @GeneratedValue
  private long id;

  private String name;

  private String surname;

  private LocalDate birthDate;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<Address> addresses;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  /** {@inheritDoc} */
  @Override
  public String getDisplayName() {
    return name + " " + surname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  @Override
  public Set<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(Set<Address> addresses) {
    this.addresses = addresses;
  }

  /**
   * Returns optional primary {@link Address}. Not used in the code directly,
   * but shows that we don't want anemic domain.
   *
   * @return the primary address.
   */
  @JsonIgnore
  @Override
  public Optional<Address> getPrimaryAddress() {
    return Optional.ofNullable(this.addresses)
        .flatMap(this::findPrimaryAddress);
  }

  private Optional<Address> findPrimaryAddress(Set<Address> addresses) {
    return addresses.stream()
        .filter(Address::isPrimary)
        .findFirst();
  }

}
