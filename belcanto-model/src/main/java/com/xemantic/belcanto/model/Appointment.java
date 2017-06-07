package com.xemantic.belcanto.model;

import javax.persistence.*;

/**
 * An appointment with the {@link Customer}.
 *
 * @author morisil
 */
@Entity
@Table(name = "APPOINTMENT")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToOne
  private Rating rating;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Rating getRating() {
    return rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }

}
