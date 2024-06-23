class DataImpl: Data<Company> {
    private val lisOfCompanies: List<Company> = createList()
    private fun createList(): List<Company> {
        var idCounterC = 1
        var idCounterV = 1
        return listOf(
            Company(idCounterC++, "Gasmyas", "Football",
                mutableListOf(
                    Vacancy(idCounterV++, ProfessionType.QA.Name, LevelType.JUNIOR.Name, 7800, "HAha vacancy"),
                    Vacancy(idCounterV++, ProfessionType.QA.Name, LevelType.MIDDLE.Name, 8800, "Hmm vacancy")
                ), "Moscow..."),
            Company(idCounterC++,"Dyldy", "Volleyball",
                mutableListOf(), "Novochepetsk..."),
            Company(idCounterC++,"Rykola", "Handball",
                mutableListOf(
                    Vacancy(idCounterV++, ProfessionType.PM.Name, LevelType.JUNIOR.Name, 6500, "The best vacancy"),
                    Vacancy(idCounterV++, ProfessionType.QA.Name, LevelType.MIDDLE.Name, 8800, "Also the best vacancy")
                ), "Piter..."),
            Company(idCounterC++,"TikTak", "Basketball",
                mutableListOf(
                    Vacancy(idCounterV++, ProfessionType.ANALYST.Name, LevelType.JUNIOR.Name, 7000, "Not a bad vacancy"),
                    Vacancy(idCounterV++, ProfessionType.QA.Name, LevelType.MIDDLE.Name, 8800, "Not the best but also good vacancy")
                ), "Belgorod..."),
            Company(idCounterC++,"Balabol", "Regbi",
                mutableListOf(
                    Vacancy(idCounterV++, ProfessionType.QA.Name, LevelType.JUNIOR.Name, 7000, "Good vacancy")
                ), "Riga...")
        )
    }

    override fun getList(): List<Company> {
        return lisOfCompanies
    }
    override fun getById(id: Int): Company? {
        return lisOfCompanies.getOrNull(id)
    }
}