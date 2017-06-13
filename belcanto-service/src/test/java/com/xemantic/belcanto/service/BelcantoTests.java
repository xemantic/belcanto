/*
 * belcanto - mini CRM system linking specialist with their customers.
 * This project explores possibilities of building Model Driven Applications
 * on top of spring-boot.
 *
 * Copyright (C) 2017  Kazimierz Pogoda
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.xemantic.belcanto.service;

import net.javacrumbs.jsonunit.ConfigurableJsonMatcher;
import net.javacrumbs.jsonunit.JsonMatchers;

/**
 * Test utilities for this project.
 *
 * @author morisil
 */
public final class BelcantoTests {

  private BelcantoTests() { /* util-class, non-instantiable */ }

  public static Long extractId(String link) {
    return Long.valueOf(link.substring(link.lastIndexOf('/') + 1));
  }

  @SuppressWarnings("RedundantStringToString")
  public static <T> ConfigurableJsonMatcher<T> jsonEquals(String expected) {
    return JsonMatchers.jsonEquals(expected.toString()); // we need to sanitize the GString
  }

}
