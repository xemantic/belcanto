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

package com.xemantic.belcanto.service.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

import java.util.Objects;

/**
 * Hamcrest {@link Matcher} operating on logical JSON structure analyzed by {@link JSONCompare}.
 *
 * @author morisil
 */
public class JsonMatcher extends TypeSafeDiagnosingMatcher<String> {

  private final String expected;

  private final JSONCompareMode mode;

  public JsonMatcher(String expected, JSONCompareMode mode) {
    this.expected = Objects.requireNonNull(expected);
    this.mode = Objects.requireNonNull(mode);
  }

  @Override
  protected boolean matchesSafely(String item, Description mismatchDescription) {
    try {
      JSONCompareResult result = JSONCompare.compareJSON(expected, item, mode);
      if (!result.failed()) {
        return true;
      }
      mismatchDescription
          .appendText("JSONs differ: \n")
          .appendText(result.getMessage());
    } catch (JSONException e) {
      mismatchDescription
          .appendText("Invalid JSON: ")
          .appendText(e.getMessage())
          .appendText("\n");
    }
    mismatchDescription
        .appendText("\n")
        .appendText("Full JSON was:\n")
        .appendText(item);
    return false;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText(expected);
  }

}
