package com.typesafe.sbt.unzip

import sbt.Keys._
import sbt._


object UnzipPlugin extends AutoPlugin {

  override def requires = empty

  private def failWithMessage(message: String): Unit =
    throw new MessageOnlyException(message)


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
      //    throw new RuntimeException("rr")
      val cReport: ConfigurationReport = (update in Compile).value.configuration(ConfigRef("compile")).get
      cReport.modules.foreach { mReport =>
      val exclusionRule = ExclusionRule(mReport.module.organization, mReport.module.name)
      if(!dependenciesToExcludeOfUnzipList.contains(exclusionRule)) {
        log.info(mReport.module.toString())
        mReport.artifacts.foreach { artifact =>
          val artifactFile = artifact._2
          log.info(artifact.toString())
          if (artifactFile.name.endsWith(".zip")) {
            log.info(s"Unpacking ${mReport.module.name} bundle: ${artifactFile.getAbsolutePath} in ${filePathToExtract}")
            IO.unzip(artifactFile, filePathToExtract)
          }
        }
      } else {
        log.debug(s"dependency ${mReport.module.toString()} exclude for unzip extraction")
      }
      }


//      cReport.modules.foreach { mReport =>
      //      log.info("NAME " + mReport.module.name)
      //        mReport.artifacts.foreach{ a=>
      //          log.info(s"Artifact NAME " + a._2.name)
      //        }
      //        if (dependenciesToExcludeOfUnzipList.contains(mReport.module.name)) {
      //          mReport.artifacts.foreach {
      //            case (_: Artifact, artifact) =>
      //              log.info(s"Unpacking ${mReport.module.name} bundle: ${artifact.getAbsolutePath} in ${testpathToExtract}")
      //              IO.unzip(artifact, testpathToExtract)
      //          }
      //        }
      //      }
    }

    unzipFiles

  }.value
  )

}
