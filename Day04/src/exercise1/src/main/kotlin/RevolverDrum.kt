import kotlin.reflect.KClass

class RevolverDrum<T: Any>(private val klass: KClass<T>) {
    private var elements: MutableList<T?> = MutableList(6) {null}
    private var pointer: Int = 0
    private var dramSize: Int = 6
    fun addOneElement(elem: T?): Boolean {
        var status = false
        for (i in 0..dramSize-1) {
            if (elements[pointer] == null) {
                elements[pointer] = elem
                status = true
                break
            }
            pointer++
            recountPointerPosition()
        }
        pointer++
        recountPointerPosition()
        return status
    }

    fun addMissingElement(source: MutableList<T?>): Boolean {
        if(source.isEmpty()) return false
        var j = 0
        for (i in dramSize-1 downTo 0) {
            pointer--
            recountPointerPosition()
            if(j >= source.size) continue // break
            if(elements[pointer] == null) {
                elements[pointer] = source[j]
                source[j] = null
                j++
            }
        }
        pointer++
        pointer++

        recountPointerPosition()
        return true
    }

    fun shoot(): Boolean { // delete item one by one
        var status = false
        if(elements[pointer] != null) {
            elements[pointer] = null
            status = true
        }
        pointer++
        recountPointerPosition()
        return status
    }

    fun unload(allElements: Boolean): List<T> { // extract a current element or all elements at once
        val extractedElements: MutableList<T> = mutableListOf()
        val countExtractedElements = if(allElements) dramSize else 1
        for (i in 0..< countExtractedElements) {
            if(elements[pointer] != null) {
                extractedElements.add(elements[pointer]!!)
            }
            elements[pointer] = null
            pointer++
            recountPointerPosition()
        }
        return extractedElements
    }

    fun scroll() {
        pointer = (0..< dramSize).random()
    }


    fun getPointer(): T? {
        return elements[pointer]
    }

    private fun recountPointerPosition() {
        pointer = (pointer + dramSize) % dramSize
    }

    companion object {
        inline operator fun <reified T: Any> invoke() = RevolverDrum(T::class)
    }

    override fun toString(): String {
        val newElements: MutableList<T?> = mutableListOf()
        var point = pointer
        for (i in 0..< dramSize) {
            point = (point + dramSize) % dramSize
            newElements.add(elements[point++])
        }

        return "Structure: ${this::class.simpleName}" +
                "<${klass.simpleName}>" +
                "\nObjects: $newElements" +
                "\nPointer: ${getPointer()}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RevolverDrum<*>

        if (klass != other.klass) return false
        if(!compareDrams(elements, other.elements)) return false
//        if (elements != other.elements) return false
////        if (pointer != other.pointer) return false
        if (dramSize != other.dramSize) return false

        return true
    }


    override fun hashCode(): Int {
        var result = klass.hashCode()
        result = 31 * result + elements.hashCode()
//        result = 31 * result + pointer
        result = 31 * result + dramSize
        return result
    }

    private fun compareDrams(elements: MutableList<T?>, other: MutableList<in Nothing?>): Boolean {
        var status = false
        for(i in 0..< dramSize) {
            status = true
            for (j in 0..<dramSize) {
                val k = (i + j + dramSize) % dramSize
                if(elements[k] != other[j]) {
                    status = false
                    break
                }
            }
            if (status) break
        }
        return status
    }

}