interface Data<T> {
    fun getList(): List<T>
    fun getById(id: Int): T?
}
