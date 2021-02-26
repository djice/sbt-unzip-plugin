import sbt.util
import xerial.sbt.Sonatype.autoImport.sonatypeCredentialHost

logLevel := util.Level.Debug

name := "sbt-unzip"
description := "Unzip dependencies"
enablePlugins(SbtPlugin)

lazy val core = project.settings(
  crossScalaVersions := List("2.13.5", "2.12.13", "2.11.12")
)



inThisBuild(
  Seq(
    name := "sbt-unzip",
    organization := "io.github.djice",
    homepage := Some(url("https://github.com/djice/sbt-unzip-plugin")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    developers := List(
      Developer(
        "djice",
        "JABOUILLE Jean Charles",
        "jcjabouille@gmail.com",
        url("https://github.com/djice")
      )
    )
  )
)