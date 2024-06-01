package exercise3

fun main(args: Array<String>) {
    val unit = args[0]

    println("Output mode: " + unit)
    println("Enter a season - (W)inter or (S)ummer:")
    var season = readln()
    if(season.lowercase() == "s") season = "Somer"
    else if(season.lowercase() == "w") season = "Winter"

    println("Season: $season. Enter a temperature:")
    val temperature = readNum()

    checkTemperature(temperature, unit, season)
}

fun readNum(): Float {
    var numb: Float
    try {
        numb = readLine()!!.toFloat()
    } catch(e: NumberFormatException) {
        println("Incorrect input. Enter a temperature:n")
        numb = readNum()
    }
    return numb
}

fun convertTemperature(temperature: Float, unit: String): Float {
    val kelvin = 273.15f
    val farengite = 32f
    var numb: Float = temperature
    when(unit) {
        "Kelvin" -> numb = temperature * kelvin
        "Fahrenheit" -> numb = temperature * farengite
//      "Celsius" -> numb = temperature
    }
    return numb
}

fun checkTemperature(temperature: Float, unit: String, season: String) {
    var tempFrom = 22
    var tempTo = 25
    if(season.lowercase() == "winter") {
        tempFrom = 20
        tempTo = 22
    }

    println()
    println("The temperature is $temperature ˚C")
    println("The comfortable temperature is from $tempFrom to $tempTo ˚C.")

    if(temperature < tempFrom)
        println("Please, make it warmer by ${convertTemperature(tempFrom - temperature, unit)} degrees.")
    else if(temperature > tempTo)
        println("Please, make it colder by ${convertTemperature(temperature - tempTo, unit)} degrees.")
    else
        println("The temperature is comfortable")
}

