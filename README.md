# sbt-unzip-plugin
==========

Extract Zip dependencies with SBT.

This plugin will extract all dependencies finishing by ".zip" extension in your base directory.

You can configure exclusion and the path where you want to extract the files.

Add plugin
----------

Version: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.djice/sbt-unzip/badge.svg)](https://maven-badges.
herokuapp.com/maven-central/io.github.djice/sbt-unzip)


Add the plugin to `project/plugins.sbt`. For example:

```scala
addSbtPlugin("com.typesafe.sbt" % "sbt-unzip" % "1.0.0")
```

In your build.sbt:

```scala
val root = (project in file(".")).enablePlugins(UnzipPlugin)
````


Configuration
-------------

### Exclusion

By default, all dependency files finishing by "."zip" will be extract. You can make an exclusion list.

In your build.sbt, add the exclusion:

```scala
excludeDependencies in unzip := Seq(
  ExclusionRule("com.jcabi", "DynamaDBLocal")
)
```

### Path of extraction

By default, the path of extraction is your project basedir, you can customize it. 

In your build.sbt, add the exclusion:

```scala
pathToExtract in unzip := new sbt.File(baseDirectory.value.getPath  + "/target/universal/stage" )
```

Run
-------------

In your build.sbt, define when the task (extract of zip) should be executed, in this example, it's before compilation:

```scala
packageBin in Compile := (packageBin in Compile dependsOn unzip).value
```

Contribution policy
-------------------

Contributions via GitHub pull requests are gladly accepted from their original
author. Before we can accept pull requests, you will need to agree to the
[Typesafe Contributor License Agreement][cla] online, using your GitHub account.


License
-------

This code is licensed under the [Apache 2.0 License][apache].


[cla]: http://www.typesafe.com/contribute/cla
[apache]: http://www.apache.org/licenses/LICENSE-2.0.html
