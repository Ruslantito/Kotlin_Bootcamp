import data.ProcessAll

fun main() {
    val start = ProcessAll()
    start.processing()

//    val processingFV = ProcessingFilterVacancy()
//    processingFV.dataProcessing()
}

//class ProcessingFilterVacancy {
//    private val fromFile = {}.javaClass.getResource("/resume.json")!!.readText()
//    fun dataProcessing() {
//        val listOfItems = Json.decodeFromString<ListOfCandidates>(fromFile)
//        println(listOfItems.candidate_info.name)
//        for (item in listOfItems.education) {
//            println(item.type)
//        }
//    }
//}
