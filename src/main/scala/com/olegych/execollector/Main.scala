package com.olegych.execollector

/**
 * @author OlegYch
 */

class Main {

  import com.roxes.win32._
  import java.io.File

  val EXE = "(.*)\\.exe$".r

  def exeFiles(root: File, depth: Int): Iterator[File] = {
    import Iterator._
    root.listFiles match {
      case null => empty;
      case ar => ar.iterator.flatMap(f => if (f.isDirectory) {
        if (depth > 0) {
          exeFiles(f, depth - 1)
        } else {
          empty
        }
      } else {
        f.getName match {
          case EXE(_) => single(f)
          case _ => empty
        }
      })
    }
  }

  def createLnk(exeFile: File, targetFolder: File): LnkFile = new Lnk() {
    exeFile.getName match {
      case EXE(n) => setName(n)
    }
    setFolder(targetFolder.getCanonicalPath)
    setIconLocation(exeFile.getAbsolutePath)
    setIconIndex(0)
    setPath(exeFile.getAbsolutePath)
    setWorkingDirectory(exeFile.getParentFile.getAbsolutePath)
  }
}

object Main extends App {
  val path = try args(1) catch {case _ => readLine("Enter root:")}
  val depth = try Integer.parseInt(args(2)) catch {case _ => 1}

  new Main {

    import java.io.File

    exeFiles(new File(path), depth).foreach(f => {
      val l = createLnk(f, new File(".").getCanonicalFile)
      println(l)
      l.save()
    })
  }
}