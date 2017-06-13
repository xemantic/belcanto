package com.xemantic.belcanto.model;

import java.util.Optional;
import java.util.Set;

/**
 * Represents abstract named party.
 *
 * @author morisil
 */
public interface Party {

  String getDisplayName();

  Optional<Address> getPrimaryAddress();

  Set<Address> getAddresses();

}
