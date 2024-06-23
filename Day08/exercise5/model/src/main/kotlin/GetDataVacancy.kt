class GetDataVacancy(private val data: Data<Company>) {

    fun getDataList(): List<VacancyInfoShort> {
        val companies = data.getList()
        val vacanciesShortInfo = mutableListOf<VacancyInfoShort>()
        companies.forEach {
            it.vacancies.forEach {v ->
                vacanciesShortInfo.add(VacancyInfoShort(v.id, v.profession, v.level, v.salary, it.name))
            }
        }
        return vacanciesShortInfo
    }
    fun getDataById(id: Int): Vacancy? {
        val companies = data.getList()
        return companies.flatMap { it.vacancies }.getOrNull(id)
    }
}
