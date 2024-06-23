class DataImpl: Data<Company> {
    private val lisOfCompanies: List<Company> = createList()
    private fun createList(): List<Company> {
        return listOf(
            Company("Gasmyas", "Football", mutableListOf(), "Moscow..."),
            Company("Dyldy", "Volleyball", mutableListOf(), "Novochepetsk..."),
            Company("Rykola", "Handball", mutableListOf(), "Piter..."),
            Company("TikTak", "Basketball", mutableListOf(), "Belgorod..."),
            Company("Balabol", "Regbi", mutableListOf(), "Riga...")
        )
    }

    override fun getCompanies(): List<Company> {
        return lisOfCompanies
    }
}