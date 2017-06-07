package com.xemantic.belcanto.model;

import javax.persistence.*;

/**
 * Customer.
 *
 * @author morisil
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer extends Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

}
