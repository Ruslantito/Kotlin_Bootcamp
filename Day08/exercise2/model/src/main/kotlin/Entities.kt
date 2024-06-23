
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
    val vacancies: MutableList<String> = mutableListOf(),  //MutableList<Vacancy>,
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
