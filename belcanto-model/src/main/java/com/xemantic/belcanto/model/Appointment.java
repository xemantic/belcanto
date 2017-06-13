package com.xemantic.belcanto.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * An appointment with the {@link Customer}.
 *
 * @author morisil
 */
@Entity
public class Appointment {

  @Id
  @GeneratedValue
  private long id;

  private LocalDateTime time;

  private Integer rating;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

}
