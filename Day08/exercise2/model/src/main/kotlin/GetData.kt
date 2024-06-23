class GetData(private val data: Data<Company>) {
    fun getList(): List<CompanyInfoShort> {
        val companies = data.getCompanies()
        val companiesShortInfo = mutableListOf<CompanyInfoShort>()
        companies.forEach {
            companiesShortInfo.add(CompanyInfoShort(it.name, it.fieldOfActivity))
        }
        return companiesShortInfo
    }

    fun getDataById(id: Int?): Company? {
        val companies = data.getCompanies()
        val companyF = companies.find { it.id == id } ?: null
        return companyF
    }
}
