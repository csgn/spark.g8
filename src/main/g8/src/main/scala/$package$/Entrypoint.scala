package $package$

import org.apache.spark.SparkContext
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

private[$package$] object $entryPointName$ extends App {
  val spark = SparkSession.builder
    .appName("$entryPointName$")
    .master("local[3]")
    .getOrCreate()

  import spark.implicits._
  val sc = spark.sparkContext

  println()
  println("============= SPARK JOB OUTPUT =============")
  println()
  Task1.run(spark, sc)
  println()
  println("============================================")
  println()

  spark.stop()
}
