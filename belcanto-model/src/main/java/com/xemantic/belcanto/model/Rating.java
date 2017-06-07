package com.xemantic.belcanto.model;

import javax.persistence.*;

/**
 * Rating of the {@link Appointment}.
 *
 * @author morisil
 */
@Entity
@Table(name = "APPOINTMENT")
public class Rating {

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
