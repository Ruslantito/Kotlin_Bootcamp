
class GetDataResume(private val data: Data<Company>) {
    fun getDataList(): List<CandidateInfo> {
        return data.getResumeList()
    }
}
