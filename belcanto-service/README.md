# The application

The [BelcantoApplication](src/main/java/com/xemantic/belcanto/service/BelcantoApplication.java)
can be started directly from the IDE.

# REST endpoints

REST services are exposed by the `spring-data-rest` and defined by `spring-data` repositories
located in the [com.xemantic.belcanto.service](src/main/java/com/xemantic/belcanto/service/repository)

* `/customers`
  * `/customers/$id/addresses`
  * `/customers/$id/specialist`
* `/specialists`
  * `/specialists/$id/addresses`
  * `/specialists/$id/customers`

# Tests

See [com.xemantic.belcanto.service](src/test/groovy/com/xemantic/belcanto/service) test
package for details.
