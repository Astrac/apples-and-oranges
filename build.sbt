lazy val scalaV = "2.11.7"

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.2.4" % "test"
    ),
    scalaVersion := scalaV
  )
