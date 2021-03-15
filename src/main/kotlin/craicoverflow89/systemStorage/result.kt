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

        // Padding Logic
        /*val padName = {value: String -> String.format("%-${categoryData.map {
            it.name.length
        }.fold(10) {
                total, length -> if(length > total) length else total
        }}s", value)}*/
        //val padSizeLength = 10
        // NOTE: this could be the largest amount of digits that are being shown
        //val padSize = {value: String -> String.format("%${padSizeLength}s", value)}

        // NOTE: just doing absolute lengths regardless of category names for now
        val padName = {value: String -> String.format("%-37s", value)}
        val padSize = {value: String -> String.format("%10s", value)}

        // Conversion Logic
        val bytesAsGB = {value: Long -> value / 1024 / 1024 / 1024}

        // Print Content
        this.add("System Storage")
        this.add("")
        this.add(createBar())
        this.add("")
        this.add("${padName("Total")}  ${padSize(bytesAsGB(driveTotal).toString())} GB")
        this.add("")
        this.addAll(categoryData.map {
            "${padName(it.name)}  ${padSize(bytesAsGB(it.size).toString())} GB"
        })
        this.add("")
        this.add("${padName("Free")}  ${padSize(bytesAsGB(driveUsable).toString())} GB")
    }.forEach {
        println(it)
    }

}

data class SystemStorageResultCategory(val name: String, val size: Long)