package com.xemantic.belcanto.model;

import javax.persistence.*;
import java.util.List;

/**
 * Customer.
 *
 * @author morisil
 */
@Entity
public class Customer extends Person {

  @OneToOne
  @JoinColumn(name = "specialist_id")
  private Specialist specialist;

  @OneToMany
  private List<Appointment> appointments;

  public Specialist getSpecialist() {
    return specialist;
  }

  public void setSpecialist(Specialist specialist) {
    this.specialist = specialist;
  }

  public List<Appointment> getAppointments() {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointments) {
    this.appointments = appointments;
  }

}
