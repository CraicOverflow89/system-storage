package craicoverflow89.systemStorage

import java.io.File

class SystemStorageReader(private val root: String) {

    fun read(): SystemStorageResult? {

        // Validate Root
        val drive = File(root)
        if(!drive.isDirectory) {
            println("Invalid path")
            return null
        }

        // Create Result
        return SystemStorageResult(drive.totalSpace, drive.usableSpace, arrayOf(
            SystemStorageResultCategory("Applications", 10737418240),
            SystemStorageResultCategory("Documents", 2147483648),
            SystemStorageResultCategory("Pictures", 3221225472)
        ))
    }

}