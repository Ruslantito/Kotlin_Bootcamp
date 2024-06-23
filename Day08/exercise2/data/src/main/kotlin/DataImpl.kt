class DataImpl: Data<Company> {
    private val lisOfCompanies: List<Company> = createList()
    private fun createList(): List<Company> {
        var idCounterC = 1
        return listOf(
            Company(idCounterC++,"Gasmyas", "Football", mutableListOf(), "Moscow..."),
            Company(idCounterC++,"Dyldy", "Volleyball", mutableListOf(), "Novochepetsk..."),
            Company(idCounterC++,"Rykola", "Handball", mutableListOf(), "Piter..."),
            Company(idCounterC++,"TikTak", "Basketball", mutableListOf(), "Belgorod..."),
            Company(idCounterC++,"Balabol", "Regbi", mutableListOf(), "Riga...")
        )
    }

    override fun getList(): List<Company> {
        return lisOfCompanies
    }
    override fun getById(id: Int): Company? {
        return lisOfCompanies.getOrNull(id)
    }
}