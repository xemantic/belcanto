package com.xemantic.belcanto.model;

import javax.persistence.*;
import java.util.List;

/**
 * Specialist.
 *
 * @author morisil
 */
@Entity
public class Specialist extends Person {

  @OneToMany
  private List<Customer> customers;

  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

}
