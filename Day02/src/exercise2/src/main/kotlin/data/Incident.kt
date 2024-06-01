import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.round

data class Incident(
    val x: Int,
    val y: Int,
    val type: IncidentType,
    val description: String?,
    var phone: String?,
) {
    init {
        phone = phone?.phoneMask()
    }
}

enum class IncidentType(val Name: String?) {
    FIRE("Fire"),
    GAS("Gas leak"),
    CAT("Cat on the tree"),
}

open class Zone() {
    open var shape: String = "base"
    open var phone: String = "88008473824".phoneMask()

    open fun incidentIsWithinIt(x: Int, y: Int): Boolean {
        println("check if incident Is With inIt BASE")
        return false
    }
}

class ZoneCircle(
    private val x: Int,
    private val y: Int,
    private val r: Int,
) : Zone() {
    override var shape: String = "circle"
    override var phone: String = "89347362826".phoneMask()
    override fun incidentIsWithinIt(xI: Int, yI: Int): Boolean {
        val d: Float = sqrt((xI.toFloat() - x.toFloat()).pow(2) + (yI.toFloat() - y.toFloat()).pow(2))
        if(d <= r) {
            return true
        }
        return false
    }
}

class ZoneTriangular(
    private val x1: Int,
    private val y1: Int,
    private val x2: Int,
    private val y2: Int,
    private val x3: Int,
    private val y3: Int,
) : Zone() {
    override var shape: String = "triangular"
    override var phone: String = "89347777777".phoneMask()

    override fun incidentIsWithinIt(xI: Int, yI: Int): Boolean {
        val areaMain: Float = calcAreaTriangle(x1, y1, x2, y2, x3, y3)
        val areaTMP1: Float = calcAreaTriangle(xI, yI, x2, y2, x3, y3)
        val areaTMP2: Float = calcAreaTriangle(x1, y1, xI, yI, x3, y3)
        val areaTMP3: Float = calcAreaTriangle(x1, y1, x2, y2, xI, yI)
        if(areaMain == (areaTMP1 + areaTMP2 + areaTMP3)) {
            return true
        }
        return false
    }

    private fun calcAreaTriangle(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int) : Float {
        val AB: Float = calcDistance(x1, y1, x2, y2)
        val BC: Float = calcDistance(x2, y2, x3, y3)
        val CA: Float = calcDistance(x3, y3, x1, y1)
        val p: Float = (AB + BC + CA) / 2
        val S: Float = sqrt((p * (p - AB) * (p - BC) * (p - CA)))
        return round(S * 100) / 100
    }

    private fun calcDistance(x1: Int, y1: Int, x2: Int, y2: Int) : Float {
        return sqrt((x2.toFloat() - x1.toFloat()).pow(2) + (y2.toFloat() - y1.toFloat()).pow(2))
    }
}

class ZoneTetragon(
    private val x1: Int,
    private val y1: Int,
    private val x2: Int,
    private val y2: Int,
    private val x3: Int,
    private val y3: Int,
    private val x4: Int,
    private val y4: Int,
    ) : Zone() {
    override var shape: String = "tetragon"
    override var phone: String = "89348888888".phoneMask()

    override fun incidentIsWithinIt(xI: Int, yI: Int): Boolean {
        val areaMain: Float = calcAreaTetragon(x1, y1, x2, y2, x3, y3, x4, y4)
        val areaTMP1: Float = calcAreaTetragon(x1, y1, x2, y2, x3, y3, x4, y4, xI, yI)
        if(areaMain == areaTMP1) {
            return true
        }
        return false
    }

    private fun calcAreaTetragon(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int, x4: Int, y4: Int) : Float {
        val areaMain1: Float = calcAreaTriangle(x1, y1, x2, y2, x3, y3)
        val areaMain2: Float = calcAreaTriangle(x1, y1, x3, y3, x4, y4)
        return areaMain1 + areaMain2
    }
    private fun calcAreaTetragon(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int, x4: Int, y4: Int, xI: Int, yI: Int) : Float {
        val areaTmp1: Float = calcAreaTriangle(xI, yI, x1, y1, x2, y2)
        val areaTmp2: Float = calcAreaTriangle(xI, yI, x2, y2, x3, y3)
        val areaTmp3: Float = calcAreaTriangle(xI, yI, x3, y3, x4, y4)
        val areaTmp4: Float = calcAreaTriangle(xI, yI, x4, y4, x1, y1)
        return areaTmp1 + areaTmp2 + areaTmp3 + areaTmp4
    }
    private fun calcAreaTriangle(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int) : Float {
        val AB: Float = calcDistance(x1, y1, x2, y2)
        val BC: Float = calcDistance(x2, y2, x3, y3)
        val CA: Float = calcDistance(x3, y3, x1, y1)
        val p: Float = (AB + BC + CA) / 2
        val S: Float = sqrt((p * (p - AB) * (p - BC) * (p - CA)))
        return round(S * 100) / 100
    }

    private fun calcDistance(x1: Int, y1: Int, x2: Int, y2: Int) : Float {
        return sqrt((x2.toFloat() - x1.toFloat()).pow(2) + (y2.toFloat() - y1.toFloat()).pow(2))
    }
}

fun String.phoneMask() : String {
    var s: String = this
    var newNumb: String = s
    when(s.length) {
        11 -> {
            if (s.substring(0..0) == "8") {
                if(s.substring(1..3) == "800") {
                    newNumb = s.substring(0..0)
                    newNumb += " ("
                    newNumb += s.substring(1..3)
                    newNumb += ") "
                    newNumb += s.substring(4..6)
                    newNumb += " "
                    newNumb += s.substring(7..8)
                    newNumb += " "
                    newNumb += s.substring(9..10)
                } else {
                    newNumb = "+7"
                    newNumb += " "
                    newNumb += s.substring(1..3)
                    newNumb += " "
                    newNumb += s.substring(4..6)
                    newNumb += "-"
                    newNumb += s.substring(7..8)
                    newNumb += "-"
                    newNumb += s.substring(9..10)
                }
            }
            else if (s.substring(0..0) == "7") {
                newNumb = "+7"
                newNumb += " "
                newNumb += s.substring(1..3)
                newNumb += " "
                newNumb += s.substring(4..6)
                newNumb += "-"
                newNumb += s.substring(7..8)
                newNumb += "-"
                newNumb += s.substring(9..10)
            }
        }
        12 -> {
            if (s.substring(0..1) == "+7") {
                newNumb = "+7"
                newNumb += " "
                newNumb += s.substring(2..4)
                newNumb += " "
                newNumb += s.substring(5..7)
                newNumb += "-"
                newNumb += s.substring(8..9)
                newNumb += "-"
                newNumb += s.substring(10..11)
            }
        }
    }
    return newNumb
}
