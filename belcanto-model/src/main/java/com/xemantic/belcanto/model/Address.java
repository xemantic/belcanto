package com.xemantic.belcanto.model;

import javax.persistence.*;

/**
 * Address.
 *
 * @author morisil
 */
@Entity
public class Address {

  @Id
  @GeneratedValue
  private long id;

  private String street;

  private String streetNumber;

  private String city;

  private String zipCode;

  @Column(name = "is_primary") // in SQL PRIMARY might be a reserved word
  private boolean primary;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public boolean isPrimary() {
    return primary;
  }

  public void setPrimary(boolean primary) {
    this.primary = primary;
  }

}
