import kotlinx.serialization.*

@Serializable
data class ListOfCandidates (
    val candidate_info: CandidateInfo,
    val education: MutableList<Education>,
    @SerialName("job_experience")
    val jobExp: MutableList<JobExperience>,
    @SerialName("free_form")
    val freeForm: String,
)

@Serializable
data class CandidateInfo(
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
