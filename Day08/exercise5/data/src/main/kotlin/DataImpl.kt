class DataImpl: Data<Company> {
    private val lisOfCompanies: List<Company> = createCompanyList()
    private val lisOfResumes: List<CandidateInfo> = createResumeList()

    private fun createCompanyList(): List<Company> {
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

    private fun createResumeList(): List<CandidateInfo> {
        var idCounterR = 1
        return listOf(
            CandidateInfo(
                idCounterR++,
                "Ivanov Ivan",
                ProfessionType.DESIGNER.Name,
                "male",
                "01/02/2000",
                Contacts("+79991117777", "email_1@gmail.com"),
                "Moscow..."
            ),
            CandidateInfo(
                idCounterR++,
                "Petrov Petya",
                ProfessionType.QA.Name,
                "male",
                "07/07/2005",
                Contacts("+79881115555", "email_2@gmail.com"),
                "Belgorod..."
            ),
            CandidateInfo(
                idCounterR++,
                "Vasechkina Vasilina",
                ProfessionType.ANALYST.Name,
                "female",
                "11/12/2002",
                Contacts("+79771114444", "email_3@gmail.com"),
                "Surgut..."
            ),
            CandidateInfo(
                idCounterR++,
                "Pinco Pallino",
                ProfessionType.PM.Name,
                "male",
                "21/05/2001",
                Contacts("+79611113333", "email_4@gmail.com"),
                "Riga..."
            ),
            CandidateInfo(
                idCounterR++,
                "Ivanova Inga",
                ProfessionType.DEVELOPER.Name,
                "female",
                "13/09/2000",
                Contacts("+79251112222", "email_5@gmail.com"),
                "Tallin..."
            ),
        )
    }

    override fun getList(): List<Company> {
        return lisOfCompanies
    }
    override fun getById(id: Int): Company? {
        return lisOfCompanies.getOrNull(id)
    }
    override fun getResumeList(): List<CandidateInfo> {
        return lisOfResumes
    }
}
