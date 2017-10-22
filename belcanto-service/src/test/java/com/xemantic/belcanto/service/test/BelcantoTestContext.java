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

import org.junit.rules.ExternalResource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Test context rule with separate instance created on behalf of each {@link BelcantoTest}s.
 *
 * @author morisil
 */
@Component
@Scope("prototype")
public class BelcantoTestContext extends ExternalResource {

  private final BelcantoTestInitializer initializer;

  private final EntityManager entityManager;

  @Inject
  public BelcantoTestContext(
      BelcantoTestInitializer initializer,
      EntityManager entityManager
  ) {
    this.initializer = initializer;
    this.entityManager = entityManager;
  }

  @Override
  protected void after() {
    // in the future will clean up resources created on behalf of each test
  }

  @Transactional
  public <T> T populate(T entity) {
    entityManager.persist(entity);
    return entity;
  }

  public int getPort() {
    return initializer.getPort();
  }

}
