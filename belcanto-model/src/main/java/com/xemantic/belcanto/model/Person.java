package com.xemantic.belcanto.model;

import javax.persistence.*;

/**
 * Represents abstract party.
 *
 * @author morisil
 */
@Entity
@Table(name = "APPOINTMENT")
public abstract class Person implements Party {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;

  private String surname;

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

}
