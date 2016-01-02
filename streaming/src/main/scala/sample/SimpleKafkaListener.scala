package sample

import kafka.serializer.StringDecoder
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._

/**
 * @author sball
 */
object SimpleKafkaListener {

  def main(args: Array[String]) {
    val Array(zkQuorum, group, topic) = args

    val sc = new SparkContext()
    val ssc = new StreamingContext(sc, Seconds(5))

    val topics = List((topic, 1)).toMap
    val kafkaStream = KafkaUtils.createStream(ssc, zkQuorum, group, topics)
    val lines = kafkaStream.map(_._2).filter(_.contains("error"))
    lines.print()

    ssc.start()
    ssc.awaitTermination()

  }
}
