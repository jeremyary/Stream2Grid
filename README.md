# Stream2Grid
============
a workable implementation of StreamToGrid with presentation layer

## Functionality:

Capture twitter stream sample to data grid and analyze trending of hashtags and frequent users utilizing simple, fast
rule sessions to persist trends so that they may be further searched against the API and analyzed.

## Stack:
 - Hazelcast in-memory data grid (persistence)
 - RabbitMQ (AMQP, queue incoming stream traffic and analysis jobs - prevent stalling in API)
 - Drools core (business rules engine, simple trend analysis)
 - Twitter4J ([Twitter API](https://dev.twitter.com/), stream and search)
 - Mockito (testing)
 - [Maven](pom.xml) (dependency mgmt)
 - Slf4j (logging)
 - EC2 Instances for Hazelcast & RabbitMQ

## Getting Started:
All you need to do is clone the project and use the command `mvn jetty:run` to start up the app.

## Current State:
All basic elements have been put into place. From here, rule sessions can be grown to further analyze Status data. The
Twitter client could be used to search for more information on detected trending hashtag and user information
to supplement what's available within the limitation of a small percentage of total twitter traffic available via the stream.

## Contact:
I can be reached at:
 - jeremy.ary@gmail.com
 - http://twitter.com/jeremyary
 - http://linkedIn.com/in/jeremyary
 - http://rulestech.net