# Domain model tests

## Test inheritance 

The hierarchy of tests follows more or less the hierarchy of model classes. I learned this technique
while browsing the source code of some libraries and Technology Compatibility Kits (TCK). Thanks to
this approach tests of concrete classes share some test cases automatically assuring Liskov substitution
principle. 

https://en.wikipedia.org/wiki/Liskov_substitution_principle

## Extra logic (non-anemic domain)

Usually domain models don't require unit testing on it's own. But as soon as any logic is introduced
to the domain it should be tested as well. See the [PersonTest](PersonTest.java) for an example.
