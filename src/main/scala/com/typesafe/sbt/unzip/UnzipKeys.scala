package com.typesafe.sbt.unzip

import sbt.TaskKey
import sbt.settingKey
import sbt.taskKey
import sbt.File



trait UnzipKeys {

  lazy val unzip: TaskKey[Unit] =
    taskKey[Unit](
      "Task to push the files specified at gitHubPagesSiteDir to the branch specified at gitHubPagesBranch."
    )

  lazy val pathToExtract = settingKey[File]("Path to extract")

}
