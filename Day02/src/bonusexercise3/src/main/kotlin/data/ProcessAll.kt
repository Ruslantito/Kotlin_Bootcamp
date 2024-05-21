package data

import Vacancy

class ProcessAll {
    fun processing() {
        val processingFV = ProcessingFilterVacancy()
        processingFV.dataProcessing()

        val vacancy: MutableMap<String, String> = mutableMapOf()
        vacancy[Vacancy::profession.name] = processingFV.listOfItems.candidateInfo.profession
        vacancy[Vacancy::seniority.name] = processingFV.getExpTotalYears().toString()

        val processingFC = ProcessingFilterCompanyByPerson(vacancy)
        processingFC.dataProcessing()
    }
}