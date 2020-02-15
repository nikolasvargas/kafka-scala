import java.util.Properties

import org.apache.kafka.clients.producer.ProducerConfig._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object Producer {
  def main(args: Array[String]): Unit = {
    writeKafka("my-topic")
  }

  def writeKafka(topic: String): Unit = {
    val producer = new KafkaProducer[String, String](properties)
    val record = new ProducerRecord[String, String](topic, "key", "mais uma")
    producer.send(record)
    producer.close()
  }

  private def properties: Properties = {
    new Properties {
      put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
      put(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
      put(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    }
  }
}
