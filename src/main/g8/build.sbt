lazy val root = project
  .in(file("."))
  .settings(
    name := "$name$",
    version := "0.0.1",
    scalaVersion := "2.12.19",
    libraryDependencies ++= Seq(
         "org.apache.spark" %% "spark-sql"  % "3.5.1",
         "org.apache.spark" %% "spark-core"  % "3.5.1",
    )
)
