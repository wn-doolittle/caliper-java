IMS Global caliper-java
==============

caliper-java is a Java client for [Caliper](http://www.imsglobal.org/caliper) that provides an implementation of the Caliper SensorAPI™.

## Building caliper-java

You can build `caliper-java` by running:
```
mvn clean install
```

Add `caliper-java` as a dependency to your project by adding:
```
<dependency>
    <groupId>org.imsglobal.caliper</groupId>
    <artifactId>caliper-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

If you are not using maven, and wish to build a jar with all the dependencies compiled, you can use the `uber-jar` build profile:
```
mvn clean -P uber-jar install
```
This will create a jar in: `target/caliper-java-{version}.jar`

## Running Tests

Note that by default, only UnitTests are run.

To run "integration" level tests, you will need to

1. Set up an instance of the Caliper reference EventStore (running at localhost:1080)
2. Comment this line in pom.xml:  
```
<groups>org.imsglobal.caliper.UnitTest</groups>
```

## Documentation

Documentation is available at [http://www.imsglobal.org/caliper](https://www.imsglobal.org/caliper).

©2017 IMS Global Learning Consortium, Inc. All Rights Reserved.  
Trademark Information - http://www.imsglobal.org/copyright.html

For license information contact, info@imsglobal.org and read the license file contained in the repository.