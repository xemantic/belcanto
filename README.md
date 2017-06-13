*mini CRM system linking specialist with their customers*

# About this project

I created this project to gain more knowledge on building
Model Driven Applications with support of [spring-boot](https://projects.spring.io/spring-boot/)
and [spring-data-rest](http://projects.spring.io/spring-data-rest/) projects.
I had prior experience using this stack for read-only APIs. This one is providing
full CRUD. I also tried to put as much *best-practice* as it was relevant for such
a project.

It's not a complete solution, rather an exercise. For this reason it's missing the
security part completely.

# Building and running
 
The project will be packaged into spring-boot's uberjar.

    $ mvn package
    $ java -jar belcanto-service/target/belcanto-service-*.jar

Note: * will resolve the version.

## Starting from the IDE

In the IDE you can start the application directly from the
[BelcantoApplication](belcanto-service/src/main/java/com/xemantic/belcanto/service/BelcantoApplication.java)
class.

# Testing and UI

## Test cases

The [UseCasesTest](belcanto-service/src/test/groovy/com/xemantic/belcanto/service/UseCasesTest.groovy)
should be self explanatory, also as a starting point for defining project requirements
and following Test Driven Development. Next to it each REST resource should be covered
with own set of CRUD integration tests.
See the
[com.xemantic.belcanto.service](belcanto-service/src/test/groovy/com/xemantic/belcanto/service)
test package for details.

## HAL browser - generic UI

The HAL browser utility is included in the build and provides a convenient way
of both, reading and writing to the API. After starting the app just point your
web browser to:

[http://localhost:8080/](http://localhost:8080/)

# Project structure

This project is organized as multi-module maven project, business model
is split from service layer.

## belcanto-model

JPA based domain model, keeping it as a separate module
will prevent from possible indirect dependency on the
service layer and will reduce the size of independent compilation unites.

See the
[com.xemantic.belcanto.model](belcanto-model/src/main/java/com/xemantic/belcanto/model)
package documentation for more information on design decisions regarding the model.

## belcanto-service

This layer is based on `spring-data-rest` library which
directly exposes JPA entities as REST repositories.

See the
[com.xemantic.belcanto.service](belcanto-service/src/main/java/com/xemantic/belcanto/service)
package for details.

# Project dependencies

* spring-boot - app lifecycle management 
* hal-browser - generic documentation and test tool for the REST resources
* JPA - for modeling the domain
* h2 database - in-memory db used in tests 
* java-hamcrest - test matchers, also for collections
* rest-assured - testing REST services with the full HTTP stack
* json-unit - JSON structural comparison

The [master pom](pom.xml) contains the full list of used dependencies.
