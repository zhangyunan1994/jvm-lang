package org.example

@Grab(group='commons-lang', module='commons-lang', version='2.4')

import org.apache.commons.lang.WordUtils
println "Hello ${WordUtils.capitalize('world')}"

def checkFilePaths = ["Downloads/baiduyun/"]

checkFilePaths.each {
  def dir = new File(System.getProperty("user.home") + "/" + it)

  def needDeleteDir = []
  // 递归遍历出所有的文件
  dir.eachDirRecurse { file ->
    if (file.name in [".git", "target"]) {
      println file.toPath()
      needDeleteDir << file
    }
  }

  needDeleteDir.each {
    it.deleteDir()
  }
}


println "执行完成"

println "ls".execute().text
