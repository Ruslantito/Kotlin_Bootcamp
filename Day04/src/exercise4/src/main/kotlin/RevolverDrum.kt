import kotlin.reflect.KClass

open class RevolverDrum<T: Bullet>(private val klass: KClass<T>) {
    private var elements: MutableList<Bullet?> = MutableList(6) {null}
    var pointer: Int = 0
    private val dramSize: Int = 6

    fun addOneElement(elem: Bullet?): Boolean {
        var status = false
        if(elem != null && elem.loadStatus) {
            println("You're wrong!")
            return false
        }
        for (i in 0..< dramSize) {
            if (elements[pointer] == null) {
                elements[pointer] = elem
                elements[pointer]?.loadStatus = true
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

    fun addMissingOneElement(source: Bullet?): Boolean {
        if(source != null && source.loadStatus) return false
        for (i in dramSize-1 downTo 0) {
            pointer--
            recountPointerPosition()
            if(elements[pointer] == null) {
                source?.loadStatus = true
                elements[pointer] = source
                break
            }
        }
        return true
    }

    fun addMissingElements(source: MutableList<Bullet?>): Boolean {
        if(source.isEmpty()) return false
        var j = 0
        for (i in dramSize-1 downTo 0) {
            pointer--
            recountPointerPosition()
            if(j >= source.size) continue
            if(elements[pointer] == null) {
                val src = source[j]
                if(src != null && !src.loadStatus) {
                    src.loadStatus = true
                    elements[pointer] = src
                }
                source[j] = null
                j++
            }
        }
        pointer++
        pointer++
        recountPointerPosition()
        source.retainAll { it != null }
        return true
    }

    fun shoot(): Boolean {
        var status = false
        val bullet = elements[pointer]
        if(bullet != null) {
            if(bullet.shootStatus) {
                println("Click. A shot one")
            } else if(bullet.dampStatus) {
                println("Click. A damp one")
            } else {
                elements[pointer]?.shootStatus = true
                elements[pointer] = null
//                bullet.shoot()
                status = true
            }
        } else {
            println("Click")
        }
        pointer++
        recountPointerPosition()
        return status
    }

    fun unload(allElements: Boolean): MutableList<Bullet?> {
        val extractedElements: MutableList<Bullet?> = MutableList(6) {null}
        val countExtractedElements = if(allElements) dramSize else 1
        for (i in 0..< countExtractedElements) {
            val bullet = elements[pointer]
            if(bullet != null) {
                bullet.loadStatus = false
                extractedElements.removeLast()
                extractedElements.add(bullet)
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

    fun ejectAllShotDamp() {
        for (i in 0..< dramSize) {
            val bullet = elements[pointer]
            if(bullet != null) {
                if(bullet.dampStatus || bullet.shootStatus || bullet.loadStatus) {
                    elements[pointer] = null
                }
            }
            pointer++
            recountPointerPosition()
        }
    }

    fun getPointer(): Bullet? {
        return elements[pointer]
    }

    fun getPointerPosition(): Int {
        return pointer
    }

    fun getDramSize(): Int {
        return dramSize
    }

    fun getDramElements(): MutableList<Bullet?> {
        return elements
    }

    fun getKlass(): KClass<T> {
        return klass
    }

    fun setDramElements(otherElements: MutableList<Bullet?>) {
        this.elements = otherElements
    }

    fun setPointerPosition(pointerPosition: Int) {
        this.pointer = pointerPosition
    }


    fun recountPointerPosition() {
        pointer = (pointer + dramSize) % dramSize
    }

    companion object {
        inline operator fun <reified T: Bullet> invoke() = RevolverDrum(T::class)
    }

    override fun toString(): String {
        val newElements: MutableList<Bullet?> = mutableListOf()
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

        if (klass != other.getKlass()) return false
        if(!compareDrams(elements, other.getDramElements())) return false
//        if (elements != other.elements) return false
////        if (pointer != other.pointer) return false
        if (dramSize != other.getDramSize()) return false

        return true
    }

    override fun hashCode(): Int {
        var result = klass.hashCode()
        result = 31 * result + elements.hashCode()
//        result = 31 * result + pointer
        result = 31 * result + dramSize
        return result
    }

    private fun compareDrams(elements: MutableList<Bullet?>, other: MutableList<out Bullet?>): Boolean {
        var status = false
        for(i in 0..< dramSize) {
            status = true
            for (j in 0..< dramSize) {
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
