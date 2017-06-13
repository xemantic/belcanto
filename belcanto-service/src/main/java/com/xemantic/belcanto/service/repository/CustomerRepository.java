package com.xemantic.belcanto.service.repository;

import com.xemantic.belcanto.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository of {@link Customer}s.
 *
 * @author morisil
 */
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  /* instantiated by spring-data-rest via reflection */
}
