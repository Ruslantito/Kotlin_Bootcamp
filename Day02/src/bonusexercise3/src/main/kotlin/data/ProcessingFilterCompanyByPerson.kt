package data

import ListOfCompanies
import Vacancy
import kotlinx.serialization.json.Json

data class ProcessingFilterCompanyByPerson(
    val vacancy: MutableMap<String, String>,
) {
    private val fromFile = {}.javaClass.getResource("/listOfCompanies.json")!!.readText()
    fun dataProcessing() {
        val listOfItems = Json.decodeFromString<ListOfCompanies>(fromFile)
        val filteredData2 = listOfItems.listOfCompanies.filter {
            it.vacancies.any { v -> (compareProfessionParam(v, vacancy) && compareSeniorityParam(v, vacancy)) }
        }

        println("Suitable vacancies:")
        filteredData2.forEach {
            c -> c.vacancies.forEach{
                    v ->
                    if(compareProfessionParam(v, vacancy) && compareSeniorityParam(v, vacancy)) {
                        println(c.name)
                        println("Field of activity:: ${c.fieldOfActivity}")
                        println("Candidate level: ${v.level}")
                        println("Salary: ${v.salary}")
                        println("Contacts: ${c.contacts}")
                        println()
                    }
            }
        }
        println()
    }

    private fun compareProfessionParam(v: Vacancy, vacancy: MutableMap<String, String>): Boolean {
        return v.profession.lowercase() == vacancy[Vacancy::profession.name]!!.lowercase()
    }

    private fun compareSeniorityParam(v: Vacancy, vacancy: MutableMap<String, String>): Boolean {
        return vacancy[Vacancy::seniority.name]!!.toInt() >= v.seniority
    }
}
