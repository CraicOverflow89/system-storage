package craicoverflow89.systemStorage

fun main() = SystemStorageResult(10, arrayOf(
    SystemStorageResultCategory("Applications", 5),
    SystemStorageResultCategory("Documents", 2),
    SystemStorageResultCategory("Pictures", 3)
)).render()