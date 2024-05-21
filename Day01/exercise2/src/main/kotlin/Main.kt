import java.lang.Exception
import kotlin.random.Random
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("Hello World!")
    println("Program arguments: ${args.joinToString()}")
    println("Enter zone parameters:")

    val enterTMP = readln().split(" ")
    var zoneF: Zone = Zone()

    val mainPhoneNumberOfZone = zoneF.phone
    try {
        zoneF = initZone(enterTMP)
    } catch(e: Exception) {
        println("Error: $e")
        exitProcess(1)
    }

    println()
    println("The zone info:")
    println("\tThe shape of zone: ${zoneF.shape}")
    println("\tPhone number: ${zoneF.phone}")
    println()
    println("Enter an incident coordinates:")

    val descriptionRandomIndex = Random.nextInt(descriptions.size)
    val descriptionRandomElem = descriptions[descriptionRandomIndex]
    val phoneRandomIndex = Random.nextInt(phones.size)
    val phoneRandomElem = phones[phoneRandomIndex]
    val incidentTypeRandomIndex = Random.nextInt(IncidentType.values().size)
    val incidentTypeRandomElem = IncidentType.values()[incidentTypeRandomIndex]

    var incidentCoorX: Int = 0
    var incidentCoory: Int = 0
    val enterIncidentZoneTMP = readln().split(";")
    try {
        incidentCoorX = enterIncidentZoneTMP[0].toInt()
        incidentCoory = enterIncidentZoneTMP[1].toInt()
    } catch(e: Exception) {
        println("Error: $e")
        exitProcess(1)
    }

    val incidentF: Incident = Incident(
        incidentCoorX,
        incidentCoory,
        incidentTypeRandomElem,
        descriptionRandomElem,
        phoneRandomElem,
    )

    println()
    println("The incident info:")
    println("\tDescription: ${incidentF.description}")
    println("\tPhone number: ${incidentF.phone}")
    println("\tType: ${incidentF.type.Name}")

    println()
    if(zoneF.incidentIsWithinIt(incidentF.x, incidentF.y)) {
        println("An incident is within the zone")
    } else {
        println("An incident is not in the zone")
        println("Switch the applicant to the common number: $mainPhoneNumberOfZone")
    }
}

fun initZone(enterTMP: List<String>) : Zone {
    var zoneF: Zone = Zone()
    when(enterTMP.size) {
        2 -> {
            val coorTMP = enterTMP[0].split(";")
            zoneF = ZoneCircle(
                coorTMP[0].toInt(), coorTMP[1].toInt(), enterTMP[1].toInt()
            )
        }
        3 -> {
            val coorTMP1 = enterTMP[0].split(";")
            val coorTMP2 = enterTMP[1].split(";")
            val coorTMP3 = enterTMP[2].split(";")
            zoneF = ZoneTriangular(
                coorTMP1[0].toInt(), coorTMP1[1].toInt(),
                coorTMP2[0].toInt(), coorTMP2[1].toInt(),
                coorTMP3[0].toInt(), coorTMP3[1].toInt()
            )
        }
        4 -> {
            val coorTMP1 = enterTMP[0].split(";")
            val coorTMP2 = enterTMP[1].split(";")
            val coorTMP3 = enterTMP[2].split(";")
            val coorTMP4 = enterTMP[3].split(";")
            zoneF = ZoneTetragon(
                coorTMP1[0].toInt(), coorTMP1[1].toInt(),
                coorTMP2[0].toInt(), coorTMP2[1].toInt(),
                coorTMP3[0].toInt(), coorTMP3[1].toInt(),
                coorTMP4[0].toInt(), coorTMP4[1].toInt()
            )
        }
        else -> {
            throw Exception ("Error: wrong parameters")
        }
    }
    return zoneF
}
