package craicoverflow89.systemStorage

import kotlin.system.exitProcess

fun main() {

    // Print Title
    SystemStorageTitle.print()

    // Read System
    print("Reading drive...")
    val result = SystemStorageReader("C:", true).read() ?: exitProcess(1)

    // Print Result
    print("\rSystem Storage\n")
    result.print()
}

class SystemStorageTitle {

    companion object {

        fun print() {
            println("")
            SystemStorageTitle::class.java.getResource("/title").readText().split("\n").forEach {
                println(it)
            }
            println("")
        }

    }

}