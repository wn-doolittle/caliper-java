caliper-java
==============

caliper-java is a java client for the Caliper Sensor API

## Running Tests

Note that by default, only UnitTests are run.

To run "integration" level tests, you will need to

1. Set up an instance of the Caliper reference EventStore (running at localhost:1080)
2. Comment this line in pom.xml:  
```
<groups>org.imsglobal.caliper.UnitTest</groups>
```

## Documentation

Documentation is available at [https://imsglobal.org/caliper/java](https://imsglobal.org/caliper/java).

..
