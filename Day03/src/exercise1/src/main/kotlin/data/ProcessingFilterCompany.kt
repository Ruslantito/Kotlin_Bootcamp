package data

import Company
import ListOfCompanies
import Vacancy
import kotlinx.serialization.json.Json

data class ProcessingFilterCompany(
    val vacancy: MutableMap<String, String>,
) {
    private val fromFile = {}.javaClass.getResource("/listOfCompanies.json")!!.readText()
    fun dataProcessing() {
        val salaryData = parseSalary( vacancy[Vacancy::salary.name]!! )
        val listOfItems = Json.decodeFromString<ListOfCompanies>(fromFile)
        val filteredData1 = listOfItems.listOfCompanies.filter { compareActivityParam(it, vacancy) }
        val filteredData2 = filteredData1.filter { it.vacancies.any { v -> compareVacancyParam(v, vacancy, salaryData) }}

        var i = 1
        filteredData2.forEach {
            c -> c.vacancies.forEach{ v ->
                if(compareVacancyParam(v, vacancy, salaryData)) {
                    println("${i++}.")
                    println(v.toString())
                    println(c.toString())
                    println("---------------------------------------")
                    println()
                }
            }
        }
        println()
    }

    private fun compareActivityParam(company: Company, vacancy: MutableMap<String, String>): Boolean {
        return when(vacancy[Company::fieldOfActivity.name]) {
            "All" -> { true }
            else -> { company.fieldOfActivity.lowercase() == vacancy[Company::fieldOfActivity.name]?.lowercase() } }
    }

    private fun compareVacancyParam(v: Vacancy, vacancy: MutableMap<String, String>, salaryData: List<Int>): Boolean {
        return (compareProfessionParam(v, vacancy))
                && (compareLevelParam(v, vacancy))
                && ( compareSalary(v, salaryData) )
    }

    private fun compareProfessionParam(v: Vacancy, vacancy: MutableMap<String, String>): Boolean {
        return when(vacancy[Vacancy::profession.name]) {
            "All" -> { true }
            else -> { v.profession.lowercase() == vacancy[Vacancy::profession.name]?.lowercase() } }
    }

    private fun compareLevelParam(v: Vacancy, vacancy: MutableMap<String, String>): Boolean {
        return when(vacancy[Vacancy::level.name]) {
            "All" -> { true }
            else -> { v.level.lowercase() == vacancy[Vacancy::level.name]?.lowercase() } }
    }

    private fun parseSalary(salary: String): List<Int> {
        val salarySplit = salary.split(" ")
        val returnRes: MutableList<Int> = mutableListOf()
        when(salarySplit[0]) {
            ">" -> {
                returnRes.add(-3)
                returnRes.add(salarySplit[1].toInt())
            }
            "<" -> {
                returnRes.add(-1)
                returnRes.add(salarySplit[1].toInt())
            }
            "All" -> {
                returnRes.add(-4)
            }
            else -> {
                returnRes.add(-2)
                returnRes.add(salarySplit[0].toInt())
                returnRes.add(salarySplit[2].toInt())
            }
        }
        return returnRes
    }

    private fun compareSalary(v: Vacancy, salaryData: List<Int>): Boolean {
        return when(salaryData[0]) {
            -1 -> { v.salary <  salaryData[1] }
            -2 -> { v.salary >= salaryData[1] && v.salary <= salaryData[2] }
            -3 -> { v.salary >  salaryData[1] }
            else -> { true } }
    }
}
