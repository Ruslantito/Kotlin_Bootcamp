
import kotlinx.serialization.*

@Serializable
data class ListOfCompanies(
    val listOfCompanies: MutableList<Company>,
)

@Serializable
data class Company(
    val id: Int,
    val name : String,
    @SerialName("field_of_activity")
    val fieldOfActivity: String, // ActivityType, //
    val vacancies: MutableList<Vacancy> = mutableListOf(),
    val contacts: String,
){
    override fun toString(): String {
        return "  $name\n  ${
            fieldOfActivity.lowercase().replaceFirstChar { it.titlecase() }
        }"
    }
}

@Serializable
data class CompanyInfoShort(
    val name : String,
    @SerialName("field_of_activity")
    val fieldOfActivity: String, // ActivityType, //
)

@Serializable
data class Vacancy(
    val id: Int,
    val profession: String, // ProfessionType, //
    val level: String,      // LevelType, //
    val salary: Int,
    val description: String,
) {
    override fun toString():String {
        return  "${level.lowercase().replaceFirstChar { it.titlecase() }} ${
            profession.lowercase().replaceFirstChar { it.titlecase() }
        }     ---      $salary"
    }
}

@Serializable
data class VacancyInfoShort(
    val id: Int,
    val profession: String, // ProfessionType, //
    val level: String,      // LevelType, //
    val salary: Int,
    val companyName: String,
    )


@Serializable
data class ListOfCandidates (
    @SerialName("candidate_info")
    val candidateInfo: CandidateInfo,
    val education: MutableList<Education>,
    @SerialName("job_experience")
    val jobExp: MutableList<JobExperience>,
    @SerialName("free_form")
    val freeForm: String,
)

@Serializable
data class CandidateInfo(
    val id: Int,
    val name : String,
    val profession: String,
    val sex: String,
    @SerialName("birth_date")
    val birthDate: String,
    val contacts: Contacts,
    val relocation: String,
)

@Serializable
data class Contacts(
    val phone : String,
    val email: String,
)

@Serializable
data class Education(
    val type : String,
    @SerialName("year_start")
    val yearStart: String,
    @SerialName("year_end")
    val yearEnd : String,
    val description: String,
)

@Serializable
data class JobExperience(
    @SerialName("date_start")
    val dateStart : String,
    @SerialName("date_end")
    val dateEnd: String,
    @SerialName("company_name")
    val companyName : String,
    val description: String,
)


enum class ActivityType(val Name: String) {
    IT("IT"),
    BANKING("Banking"),
    PS("Public services"),
//    ALL("All");
}

enum class ProfessionType(val Name: String) {
    DEVELOPER("Developer"),
    QA("QA"),
    PM("Project Manager"),
    ANALYST("Analyst"),
    DESIGNER("Designer"),
//    ALL("All");
}

enum class LevelType(val Name: String) {
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior"),
//    ALL("All");
}

enum class SalaryType(val Name: String) {
    LESS("< 100000"),
    MIDDLE("100000 - 150000"),
    MORE("> 150000"),
//    ALL("All");
}
