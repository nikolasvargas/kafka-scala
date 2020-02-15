import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig._
import org.apache.kafka.clients.consumer._
import scala.jdk.CollectionConverters._

object Consumer {
  def main(args: Array[String]): Unit = {
    consumerFromKafka("my-topic")
  }

  private var running = true

  def consumerFromKafka(topic: String): Unit = {
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](properties)
    consumer.subscribe(util.Arrays.asList(topic))
    while (running) {
      val rec = consumer.poll(100)
      for (r <- rec.asScala) {
        if (!r.value().isEmpty)
        println(r.key(), r.value(), r.offset())
      }
    }
  }

  private def properties: Properties = {
    new Properties {
      put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
      put(KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
      put(VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
      put(AUTO_OFFSET_RESET_CONFIG, "latest")
      put(GROUP_ID_CONFIG, "simpleUser")
    }
  }
}
