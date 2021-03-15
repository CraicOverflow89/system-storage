package craicoverflow89.systemStorage

fun main() = SystemStorageResult(10, arrayOf(
    SystemStorageCategoryData("Applications", 5),
    SystemStorageCategoryData("Documents", 2),
    SystemStorageCategoryData("Pictures", 3)
)).render()