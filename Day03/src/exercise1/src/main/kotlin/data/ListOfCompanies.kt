import kotlinx.serialization.*

@Serializable
data class ListOfCompanies(
    val listOfCompanies: MutableList<Company>,
)

@Serializable
data class Company(
    val name : String,
    @SerialName("field_of_activity")
    val fieldOfActivity: String, // ActivityType, //
    val vacancies: MutableList<Vacancy>,
    val contacts: String,
){
    override fun toString(): String {
        return "  $name\n  ${
            fieldOfActivity.lowercase().replaceFirstChar { it.titlecase() }
        }"
    }
}

@Serializable
data class Vacancy(
    val profession: String, // ProfessionType, //
    val level: String, // LevelType, //
    val salary: Int,
) {
    override fun toString():String {
        return  "${level.lowercase().replaceFirstChar { it.titlecase() }} ${
            profession.lowercase().replaceFirstChar { it.titlecase() }
        }     ---      $salary"
    }

}

enum class ActivityType(val Name: String) {
    IT("IT"),
    BANKING("Banking"),
    PS("Public services"),
    ALL("All");
}

enum class ProfessionType(val Name: String) {
    DEV("Developer"),
    QA("QA"),
    PM("Project Manager"),
    ANALYST("Analyst"),
    DESIGNER("Designer"),
    ALL("All");
}

enum class LevelType(val Name: String) {
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior"),
    ALL("All");
}

enum class SalaryType(val Name: String) {
    LESS("< 100000"),
    MIDDLE("100000 - 150000"),
    MORE("> 150000"),
    ALL("All");
}