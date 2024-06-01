import kotlin.reflect.KProperty

data class First(val name: String)
data class Second(val name: String)

fun compareObjByTypes(object1: Any, object2: Any): Boolean {
    return object1::class == object2::class
}

abstract class MapperClass<A, B> {
    abstract fun convertAtoB(a: A): B
}

class ConvertListAtoB: MapperClass<First, Second>() {
    override fun convertAtoB(a: First): Second {
        return Second(a.name)
    }
    fun convertListsAtoB(a: List<First>): List<Second> {
        val b: MutableList<Second> = mutableListOf()
        a.forEach{ b.add(convertAtoB(it)) }
        return b.toList()
    }
}

inline fun <reified certainType> Any.isObjectIsOfCertainType(): Boolean {
    return this is certainType
}

class DelegateAndPlus {
    var description: String by ReplaceDelegate()
    var logs: String by LogsDelegate()
}

class ReplaceDelegate {
    private var valueL: String = String()
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return valueL
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        valueL = value.replace(" ", "_")
    }
}

class LogsDelegate {
    private var valueL: String = String()
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("A property \"$valueL\" was readed")
        return valueL
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        valueL = value
        println("A property was assigned \"$valueL\"")
    }
}

fun addition(a: Number, b: Number): Number {
    return (a.toDouble() + b.toDouble())
}
fun subtraction(a: Number, b: Number): Number {
    return (a.toDouble() - b.toDouble())
}
fun multiplication(a: Number, b: Number): Number {
    return (a.toDouble() * b.toDouble())
}
fun division(a: Number, b: Number): Number {
    if (b == 0.0) throw ArithmeticException("Warning!!!\nDivision by zero ((((")
    return (a.toDouble() / b.toDouble())
}
enum class ArithmeticAction(val count: (Number, Number) -> Number) {
    ADDITION({a, b -> addition(a, b)}),
    SUBTRACTION({a, b -> subtraction(a, b)}),
    MULTIPLICATION({a, b -> multiplication(a, b)}),
    DIVISION({a, b -> division(a, b)}),
}
fun chooseOperator(selected: String): ((Number, Number) -> Number)? {
    return ArithmeticAction.entries.find { it.name == selected }?.count
}
