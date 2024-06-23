class GetData(private val data: Data<Company>) {
    fun getList(): List<CompanyInfoShort> {
        val companies = data.getCompanies()
        val companiesShortInfo = mutableListOf<CompanyInfoShort>()
        companies.forEach {
            companiesShortInfo.add(CompanyInfoShort(it.name, it.fieldOfActivity))
        }
        return companiesShortInfo
    }
}