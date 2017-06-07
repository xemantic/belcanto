*simple web service (api) which allows audiologists (hearing careprofessionals) to manage their customers appointments
– a mini CRM.*

# Building and running
 
The project will be packaged into spring-boot's uberjar.

    $ mvn package
    $ java -jar belcanto-service/target/belcanto-service-*.jar

Note: * will resolve the version.

# Testing and UI

The HAL browser utility is included in the build. In
order to test REST services, after running the app point
your browser to:

[http://localhost:8080/](http://localhost:8080/)

# project structure

It is multi-module maven project, business model
is split from service layer logic.

## belcanto-model

JPA based domain model, keeping it as a separate module
will prevent from possible indirect dependency on the
service layer

## belcanto-service

This layer is based on `spring-data-rest` library which
directly exposes JPA entities as REST repositories.
