class DataImpl: Data<Company> {
    private val lisOfCompanies: List<Company> = createList()
    private fun createList(): List<Company> {
        return listOf(
            Company(1,"Gasmyas", "Football", mutableListOf(), "Moscow..."),
            Company(2,"Dyldy", "Volleyball", mutableListOf(), "Novochepetsk..."),
            Company(3,"Rykola", "Handball", mutableListOf(), "Piter..."),
            Company(4,"TikTak", "Basketball", mutableListOf(), "Belgorod..."),
            Company(5,"Balabol", "Regbi", mutableListOf(), "Riga...")
        )
    }

    override fun getCompanies(): List<Company> {
        return lisOfCompanies
    }
}