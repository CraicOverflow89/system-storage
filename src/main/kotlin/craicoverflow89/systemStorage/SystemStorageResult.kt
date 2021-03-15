package craicoverflow89.systemStorage

class SystemStorageResult(private val total: Int, private val categoryData: Array<SystemStorageResultCategory>) {

    private fun padLeft(value: String, length: Int) = String.format("%${length}s", value)

    private fun padRight(value: String, length: Int) = String.format("%-${length}s", value)

    fun render() = ArrayList<String>().apply {
        this.add("System Storage")
        this.add("")
        val paddingName = categoryData.map {
            it.name.length
        }.fold(0) {
                total, length -> if(length > total) length else total
        }
        val paddingSize = 5
        this.add("${padRight("Total", paddingName)}  ${padLeft(total.toString(), paddingSize)}")
        this.addAll(categoryData.map {
            "${padRight(it.name, paddingName)}  ${padLeft(it.size.toString(), paddingSize)}"
        })
    }.forEach {
        println(it)
    }

}

data class SystemStorageResultCategory(val name: String, val size: Int)