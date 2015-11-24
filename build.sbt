name := """scala-demo"""

version := "1.0"

scalaVersion := "2.10.6"

// Change this to another test framework if you prefer

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "com.typesafe.play" % "play-json_2.10" % "2.4.3"
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"



fork in run := true