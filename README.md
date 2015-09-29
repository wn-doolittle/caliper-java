IMS Global caliper-java
==============
NOTE: THESE MATERIALS ARE FOR IMS CONTRIBUTING MEMBERS ONLY. THEY MAY NOT BE RELEASED UNTIL APPROVED BY IMS GLOBAL. 

caliper-java is a java client for the IMS Global Caliper Analytics™ Sensor API

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
    <version>1.0.0-SNAPSHOT</version>
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

Documentation is available at [http://imsglobal.org/caliper/java](https://imsglobal.org/caliper/java).

©2015 IMS Global Learning Consortium, Inc. All Rights Reserved.  Trademark Information - http://www.imsglobal.org/copyright.html