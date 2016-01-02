# Assorted samples for streaming

To build all the samples:
sbt assembly

## Simple Kafka Listener

This is a very basic kafka listener, which uses the receiver method. (Note you should really be using the new direct method).

spark-submit --master yarn-client --class sample.App target/scala-2.10/streaming-assembly-0.0.1.jar <zookeepers> <groupid> <topic>

where:
 <zookeepers> is a comma separated list of host:port pairs
 <groupid> is the consumer group to use
 <topic> is an existing topic, preferably which receives messages that are lines of text, with "error" in them somewhere

Tested on HDP 2.3.2, using spark 1.4.1.
