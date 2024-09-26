package $package$

import org.apache.spark.SparkContext
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming._

private[$package$] object Task1 {
  def run(
      spark: SparkSession,
      sparkContext: SparkContext
  )(waitSparkUI: => Unit) = {
    println("HELLO WORLD")
  }

  def runStream(spark: SparkSession, sparkContext: SparkContext)(
      waitSparkUI: => Unit
  ) = {
    // • Input Source  //
    val source = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9876) // nc -l 9876
      .load()

    // • Data Transformation  //
    val resultDF = source
      .select(split(expr("value"), "\\s").as("word"))
      .groupBy("word")
      .count()

    // • Output Sink    //
    val writer = resultDF.writeStream
      .format("console")
      .trigger(Trigger.ProcessingTime("1 second"))
      .outputMode("complete")

    // • Start Stream    //
    val streamingQuery = writer.start()
    streamingQuery.awaitTermination(10000 /* 10sec */ )

    // • Tidy up    //
    streamingQuery.stop()
  }
}
