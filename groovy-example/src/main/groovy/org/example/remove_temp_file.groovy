
def checkFilePaths = ["project", "WebstormProjects"]

checkFilePaths.each {
  def dir = new File(System.getProperty("user.home") + "/" + it)

  def needDeleteDir = []
  // 递归遍历出所有的文件
  dir.eachDirRecurse { file ->
    if (file.name in ["node_modules", "target"]) {
      println file.toPath()
      needDeleteDir << file
    }
  }

  needDeleteDir.each {
    it.deleteDir()
  }
}

