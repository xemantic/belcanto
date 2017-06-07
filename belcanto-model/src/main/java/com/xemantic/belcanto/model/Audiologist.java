package com.xemantic.belcanto.model;

import javax.persistence.*;

/**
 * Audiologist.
 *
 * @author morisil
 */
@Entity
@Table(name = "AUDIOLOGIST")
public class Audiologist extends Person {

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
