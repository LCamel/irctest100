name := """irctest"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "com.ircclouds.irc" % "irc-api" % "1.0-0014",
  jdbc,
  anorm,
  cache,
  ws
)
