package data

import ListOfCandidates
import kotlinx.serialization.json.Json
import java.io.File

class ProcessingFilterVacancy {
//	private val fromFile = {}.javaClass.getResource("/resume.json")!!.readText()
    private val fromFile = File("src/files/resume.json").readText()

    fun dataProcessing() {
        if(fromFile.isNotBlank()) {
            val listOfItems = Json.decodeFromString<ListOfCandidates>(fromFile)
            println("Block 1")
            println(listOfItems.candidateInfo.toString())

            println()
            println("Block 2")
            println(listOfItems.education.toString())

            println()
            println("Block 3")
            println(listOfItems.jobExp.toString())

            println()
            println("Block 4")
            println(listOfItems.freeForm)
        }
    }
}
