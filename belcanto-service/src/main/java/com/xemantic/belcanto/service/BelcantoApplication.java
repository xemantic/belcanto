package com.xemantic.belcanto.service;

import com.xemantic.belcanto.model.Customer;
import com.xemantic.belcanto.service.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application starter.
 * <p>
 *   Note: can be used in development.
 * </p>
 *
 * @author morisil
 */
@SpringBootApplication
@EntityScan(basePackageClasses = Customer.class)
@EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
//@ComponentScan("com.bonial.api.content")
public class BelcantoApplication {

  public static void main(String ... args) {
    SpringApplication.run(BelcantoApplication.class, args);
  }

}
