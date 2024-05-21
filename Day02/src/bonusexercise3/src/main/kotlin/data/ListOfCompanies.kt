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
        return name +
                "\nField of activity: ${fieldOfActivity.lowercase().replaceFirstChar { it.titlecase() }} " +
                "\n${fieldOfActivity.toString()}" +
                "\nContacts: $contacts"
    }
}

@Serializable
data class Vacancy(
    val profession: String, // ProfessionType, //
    val level: String, // LevelType, //
    val salary: Int,
    val seniority: Int =
        when(level.uppercase()) {
            ExpType.JUNIOR.name -> ExpType.JUNIOR.value
            ExpType.MIDDLE.name -> ExpType.MIDDLE.value
            ExpType.SENIOR.name -> ExpType.SENIOR.value
            else -> -1
        }
) {
    override fun toString():String {
        return  "Candidate level: ${level.lowercase().replaceFirstChar { it.titlecase() }}" +
                "Salary: $salary"
    }

}

enum class ActivityType(val Name: String) {
    IT("IT"),
    BANKING("Banking"),
    PS("Public services"),
    ALL("All"),
}

enum class ProfessionType(val Name: String) {
    DEV("Developer"),
    QA("QA"),
    PM("Project Manager"),
    ANALYST("Analyst"),
    DESIGNER("Designer"),
    ALL("All"),
}

enum class LevelType(val Name: String) {
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior"),
    ALL("All"),
}

enum class SalaryType(val Name: String) {
    LESS("< 100000"),
    MIDDLE("100000 - 150000"),
    MORE("> 150000"),
    ALL("All"),
}

enum class ExpType(val value: Int) {
    JUNIOR(0),
    MIDDLE(3),
    SENIOR(6),
}
