package craicoverflow89.systemStorage

import java.io.File
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import java.util.concurrent.atomic.AtomicLong

class SystemStorageReader(private val root: String, private val updateTerminal: Boolean = false) {

    // https://stackoverflow.com/questions/2149785/get-size-of-folder-or-file/19877372#19877372
    private fun directorySize(directory: File): Long {
        val path = directory.toPath()
        val size = AtomicLong(0)
        try {
            Files.walkFileTree(path, object: SimpleFileVisitor<Path>() {
                override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
                    size.addAndGet(attrs.size())
                    return FileVisitResult.CONTINUE
                }

                override fun visitFileFailed(file: Path, exc: IOException?): FileVisitResult {
                    //println("skipped: $file ($exc)")
                    // Skip folders that can't be traversed
                    return FileVisitResult.CONTINUE
                }

                override fun postVisitDirectory(dir: Path, exc: IOException?): FileVisitResult {
                    //if(exc != null) println("had trouble traversing: $dir ($exc)")
                    // Ignore errors traversing a folder
                    return FileVisitResult.CONTINUE
                }
            })
        } catch(e: IOException) {
            throw AssertionError("walkFileTree will not throw IOException if the FileVisitor does not")
        }
        return size.get()
    }

    fun read(): SystemStorageResult? {

        // Validate Root
        val drive = File(root)
        if(!drive.isDirectory) {
            println("Invalid path")
            return null
        }

        // Create Result
        return SystemStorageResult(drive.totalSpace, drive.usableSpace, ArrayList<SystemStorageResultCategory>().apply {
            val categoryAdd = {name: String, logic: () -> Long ->
                if(updateTerminal) print("\rCalculating size of $name...")
                this.add(SystemStorageResultCategory(name, logic()))
            }
            categoryAdd("Operating System", {
                directorySize(File("C:/Windows"))
            })
            categoryAdd("Applications", {
                directorySize(File("C:/Program Files")) + directorySize(File("C:/Program Files (x86)"))
            })
            categoryAdd("User Files", {
                directorySize(File("C:/Users"))
            })
        }.toTypedArray())
    }

}