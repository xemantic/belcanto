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

import io.restassured.RestAssured;
import io.restassured.config.MatcherConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Initializes context of {@link BelcantoTest}s. A lazy singleton which is initialized only once on the startup
 * of shared test container.
 *
 * @author morisil
 */
@Component
@Lazy
// thanks to @Lazy it will be created very late in the container lifecycle, after establishing HTTP port
public class BelcantoTestInitializer {

  private final int port;

  @Inject
  public BelcantoTestInitializer(@LocalServerPort int port) {
    this.port = port;
  }

  @PostConstruct
  public void initialize() {
    initializeRestAssured();
  }

  public int getPort() {
    return port;
  }

  private void initializeRestAssured() {
    RestAssured.port = port;
    RestAssured.config = RestAssuredConfig
        .config()
        .matcherConfig(
            MatcherConfig
                .matcherConfig()
                .errorDescriptionType(MatcherConfig.ErrorDescriptionType.HAMCREST)
        );
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
  }

}
