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

    fun print() = ArrayList<String>().apply {

        // Render Logic
        val lineBreak = {this.add("")}
        val categoryLine = {
            val bytesAsGB = {value: Long -> value / 1024.0 / 1024.0 / 1024.0}
            val padName = {value: String -> String.format("%-37s", value)}
            val padSize = {value: Double -> String.format("%10.2f", value)}
            {name: String, size: Long -> "${padName(name)}  ${padSize(bytesAsGB(size))} GB"}
        }()

        // Create Content
        lineBreak()
        this.add(createBar())
        lineBreak()
        this.add(categoryLine("Total Space", driveTotal))
        lineBreak()
        this.addAll(categoryData.map {
            categoryLine(it.name, it.size)
        })
        // NOTE: Other Files could be a category in the result object
        this.add(categoryLine("Other Files", driveTotal - driveUsable - categoryData.fold(0L) {size, data ->
            size + data.size
        }))
        lineBreak()
        this.add(categoryLine("Free Space", driveUsable))
    }.forEach {
        println(it)
    }

}

data class SystemStorageResultCategory(val name: String, val size: Long)