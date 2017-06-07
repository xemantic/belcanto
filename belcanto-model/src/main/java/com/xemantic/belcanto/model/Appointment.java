package com.xemantic.belcanto.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * An appointment with the {@link Customer}.
 *
 * @author morisil
 */
@Entity
@Table(name = "APPOINTMENT")
public class Appointment {

  private Rating rating;

}
