package craicoverflow89.systemStorage

import kotlin.math.round

class SystemStorageResult(private val driveTotal: Long, private val driveUsable: Long, private val categoryData: Array<SystemStorageResultCategory>) {
    private val driveUsablePercent = round((driveUsable.toDouble() / driveTotal.toDouble()) * 100).toInt()

    private fun createBar() = StringBuffer().apply {
        this.append("[")
        this.append("#".repeat(50 - (driveUsablePercent / 2)))
        this.append("-".repeat(driveUsablePercent / 2))
        this.append("]")
    }.toString()

    private fun createTitle() = javaClass.getResource("/title").readText().split("\n")

    fun print() = ArrayList<String>().apply {

        // Render Logic
        val lineBreak = {this.add("")}
        val bytesAsGB = {value: Long -> value / 1024 / 1024 / 1024}
        val padName = {value: String -> String.format("%-37s", value)}
        val padSize = {value: String -> String.format("%10s", value)}
        val categoryLine = {name: String, size: Long -> "${padName(name)}  ${padSize(bytesAsGB(size).toString())} GB"}

        // Create Content
        lineBreak()
        this.addAll(createTitle())
        lineBreak()
        this.add(createBar())
        lineBreak()
        this.add(categoryLine("Total", driveTotal))
        lineBreak()
        this.addAll(categoryData.map {
            categoryLine(it.name, it.size)
        })
        lineBreak()
        this.add(categoryLine("Free", driveUsable))
    }.forEach {
        println(it)
    }

}

data class SystemStorageResultCategory(val name: String, val size: Long)