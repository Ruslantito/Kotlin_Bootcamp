package data

import ListOfCandidates
import kotlinx.serialization.json.Json
import JobExperience
import java.io.File

class ProcessingFilterVacancy(
//    private val fromFile: String = {}.javaClass.getResource("/resume.json").readText(),
    private val fromFile: String = File("src/files/resume.json").readText(),

    val listOfItems: ListOfCandidates = Json.decodeFromString<ListOfCandidates>(fromFile),
    private var expTotalYears: Int = 0
    ) {

    fun dataProcessing() {
        val monthsOfExpTotalMonths = countExpPeriodMonths(listOfItems.jobExp)
        val expYears = monthsOfExpTotalMonths / 12
        val expMonths = monthsOfExpTotalMonths % 12
        expTotalYears = expYears + (if(expMonths > 0) 1 else 0)

        println("The candidate:")
        println("Name: ${listOfItems.candidateInfo.name}") // Vasiliev Sergei Petrovich
        println("Profession: ${listOfItems.candidateInfo.profession}") //QA
        println("Experience: $expYears year $expMonths months") // 1 year 5 months (junior)

    }

    fun getExpTotalYears(): Int {
        return this.expTotalYears
    }

    private fun countExpPeriodMonths(jobExp: MutableList<JobExperience>): Int {
        var countExperiences = 1
        for(job in jobExp) {
            countExperiences += countDateDifference(job.dateStart, job.dateEnd)
        }
        return countExperiences
    }

    private fun countDateDifference(dataStrStart:String, dataStrEnd:String): Int {
        val dataResStart = dataStrStart.split(".")
        val dataResEnd = dataStrEnd.split(".")
        val resYears = dataResEnd[1].toInt() - dataResStart[1].toInt()
        return resYears * 12 + dataResEnd[0].toInt() - dataResStart[0].toInt()
    }
}
