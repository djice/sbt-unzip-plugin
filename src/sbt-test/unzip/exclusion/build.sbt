//logLevel := Level.Debug

val root = (project in file(".")).enablePlugins(JavaAppPackaging, UnzipPlugin)

excludeDependencies in unzip := Seq(
 ExclusionRule("com.jcabi", "DynamaDBLocal")
)
pathToExtract in unzip := new sbt.File(baseDirectory.value.getPath  + "/target/universal/stage" )

libraryDependencies ++= Seq(
 "com.jcabi" % "DynamoDBLocal" % "2015-07-16"

)

stage := (stage dependsOn unzip).value
