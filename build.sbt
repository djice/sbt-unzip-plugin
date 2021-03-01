organization := "com.typesafe.sbt"
name := "sbt-unzip"
description := "sbt-web plugin for gzipping assets"
enablePlugins(JavaAppPackaging)
enablePlugins(SbtPlugin)
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.8.0")
//addSbtWeb("1.4.2")
