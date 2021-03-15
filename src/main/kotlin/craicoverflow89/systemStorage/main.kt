package craicoverflow89.systemStorage

import kotlin.system.exitProcess

fun main() {

    // Read System
    val result = SystemStorageReader("J:").read() ?: exitProcess(1)

    // Print Result
    result.print()
}