package com.xemantic.belcanto.service.repository;

import com.xemantic.belcanto.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository of {@link Specialist}s.
 *
 * @author morisil
 */
@RepositoryRestResource
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
  /* instantiated by spring-data-rest via reflection */
}
