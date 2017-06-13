# Domain model

So called *analysis patterns* are supposed to cover the design of common reusable domain models like
relationships between parties and organizational structures.

https://martinfowler.com/books/ap.html
https://martinfowler.com/apsupp/accountability.pdf

The model of this example project is not complete in any sense and it might have flaws. But it should
provide a good starting point.

## Non-anemic domain

Even though I prefer to strictly separate the domain model from the service layer, and for this
reason the model resides in the separate compilation unit, still I believe that it is worth to make
the model *non-anemic* to certain extent. Some examples are provided in the code of the
[Person](Person.java) class. All the extra logic is covered by the
[unit tests](../../../../../../test/java/com/xemantic/belcanto/model).

https://en.wikipedia.org/wiki/Anemic_domain_model
