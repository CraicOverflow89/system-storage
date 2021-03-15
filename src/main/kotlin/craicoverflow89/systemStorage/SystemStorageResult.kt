package craicoverflow89.systemStorage

data class SystemStorageCategoryData(val name: String, val size: Int)

class SystemStorageResult(private val total: Int, private val categoryData: Array<SystemStorageCategoryData>) {

    private fun padRight(value: String, length: Int) = String.format("%-${length}s", value)

    fun render() = ArrayList<String>().apply {
        this.add("System Storage")
        this.add("")
        val paddingLength = categoryData.map {
            it.name.length
        }.fold(0) {
                total, length -> if(length > total) length else total
        }
        this.add("${padRight("Total", paddingLength)}  $total")
        this.addAll(categoryData.map {
            "${padRight(it.name, paddingLength)}  ${it.size}"
        })
    }.forEach {
        println(it)
    }

}