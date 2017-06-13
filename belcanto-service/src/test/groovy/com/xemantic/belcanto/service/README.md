# Why integration tests are written in Groovy?

It is much more convenient to write unit test assertions against JSON in Groovy than in
Java. In case of tests the maximum performance is not critical. REST endpoint tests are
actually integration tests, which are slower by nature, and therefore additional overhead
for groovy compilation is negligible.

# Why not Spring MockMvc?

When using the full spring web environment test:

    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 
, the whole servlet container is started, which will assure that all the HTTP stack
is operational and all the spring logic layers are aligned like in production environment.
It prevents from undetected quirks of the runtime on production like inconsistencies in:

 * filters
 * spring security
 * JSON serialization
 * transaction management
 * etc.

# RestAssured for HTTP/JSON based assertions

[RestAssured](http://rest-assured.io/) library is used for performing and verifying the HTTP
requests against REST resources. It is also convenient for extracting information from the
JSON payload.

