package com.typesafe.sbt.unzip

import sbt.Keys._
import sbt._


object UnzipPlugin extends AutoPlugin {

  override def requires = empty

  object autoImport extends UnzipKeys

  import autoImport._

  override def trigger = AllRequirements

  override lazy val globalSettings: Seq[Def.Setting[_]] = Seq(
    excludeDependencies := Seq(),
    pathToExtract := (ThisBuild / baseDirectory).value
  )

  override lazy val projectSettings: Seq[Def.Setting[_]] = Seq(

    unzip := Def.taskDyn {
      val unzipFiles = Def.task{
  
      val dependenciesToExcludeOfUnzipList: Seq[ExclusionRule] = (excludeDependencies in unzip).value
        val filePathToExtract = (pathToExtract in unzip).value
        val log = streams.value.log

        val cReport: ConfigurationReport = (update in Compile).value.configuration(ConfigRef("compile")).get
        cReport.modules.foreach { mReport =>
          val exclusionRule = ExclusionRule(mReport.module.organization, mReport.module.name)
          if(!dependenciesToExcludeOfUnzipList.contains(exclusionRule)) {
            mReport.artifacts.foreach { artifact =>
              val artifactFile = artifact._2
              if (artifactFile.name.endsWith(".zip")) {
                log.info(s"Unpacking ${mReport.module.name} bundle: ${artifactFile.getAbsolutePath} in ${filePathToExtract}")
                IO.unzip(artifactFile, filePathToExtract)
              }
            }
          } else {
            log.debug(s"dependency ${mReport.module.toString()} exclude for unzip extraction")
          }
        }

      }
      unzipFiles
    }.value
  )

}
