class GetDataCompany(private val data: Data<Company>) {
    fun getDataList(): List<CompanyInfoShort> {
        val companies = data.getList()
        val companiesShortInfo = mutableListOf<CompanyInfoShort>()
        companies.forEach {
            companiesShortInfo.add(CompanyInfoShort(it.name, it.fieldOfActivity))
        }
        return companiesShortInfo
    }

    fun getDataById(id: Int): Company? {
        return data.getById(id)
    }
}
