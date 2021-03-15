package craicoverflow89.systemStorage

class SystemStorageResult(private val total: Int, private val categoryData: Array<SystemStorageResultCategory>) {

    fun render() = ArrayList<String>().apply {

        // Padding Logic
        val padName = {value: String -> String.format("%-${categoryData.map {
            it.name.length
        }.fold(0) {
                total, length -> if(length > total) length else total
        }}s", value)}
        val padSizeLength = 5
        val padSize = {value: String -> String.format("%${padSizeLength}s", value)}

        // Print Content
        this.add("System Storage")
        this.add("")
        this.add("${padName("Total")}  ${padSize(total.toString())}")
        this.addAll(categoryData.map {
            "${padName(it.name)}  ${padSize(it.size.toString())}"
        })
    }.forEach {
        println(it)
    }

}

data class SystemStorageResultCategory(val name: String, val size: Int)