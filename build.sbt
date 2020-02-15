name := "hello"

version := "0.1"

scalaVersion := "2.13.1"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka
libraryDependencies ++= Seq(
  "org.apache.kafka" %% "kafka" % "2.4.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime,
)