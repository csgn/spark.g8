package $package$

import org.apache.spark.SparkContext
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

private[$package$] object $entryPointName$ extends App {
  val spark = SparkSession.builder
    .appName("$entryPointName$")
    .master("local[3]")
    .getOrCreate()

  val sc = spark.sparkContext

  def waitSparkUI: Unit = {
    println("Serving at http://localhost:4040")
    println("Press enter in order to stop task.")
    scala.io.StdIn.readLine()
  }

  println()
  println("============= SPARK JOB OUTPUT =============")
  println()
  Task1.run(spark, sc)(waitSparkUI)
  println()
  println("============================================")
  println()

  spark.stop()
}
