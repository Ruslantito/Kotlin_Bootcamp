package com.example.thermohydrometer

class Thermohydrometer {

    fun main(args: Array<String>) {
        val unit = args[0]

        println("Output mode: $unit")
        println("Enter a season - (W)inter or (S)ummer:")
        var season = readln()
        if(season.lowercase() == "s") season = "Summer"
        else if(season.lowercase() == "w") season = "Winter"

        println("Season: $season. Enter a temperature:")
        val temperature = readNum()

        println("Enter humidity:")
        val humidity = readNum()
        println("humidity: $humidity")

        checkTemperature(temperature, unit, season)
        checkHumidity(humidity, season)
    }

    private fun readNum(): Float {
        var numb: Float
        try {
            numb = readln().toFloat()
        } catch(e: NumberFormatException) {
            println("Incorrect input. Enter a temperature:n")
            numb = readNum()
        }
        return numb
    }

    private fun convertTemperature(temperature: Float, unit: String): Float {
        val kelvin = 273.15f
        val fahrenheit = 32f
        var numb: Float = temperature
        when(unit) {
            "Kelvin" -> numb = temperature * kelvin
            "Fahrenheit" -> numb = temperature * fahrenheit
//      "Celsius" -> numb = temperature
        }
        return numb
    }

    private fun checkTemperature(temperature: Float, unit: String, season: String) {
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

    private fun checkHumidity(humidity: Float, season: String) {
        val humidityFrom = 30
        var humidityTo = 60
        if(season.lowercase() == "winter") humidityTo = 45

        println()
        println("The comfortable humidity is from $humidityFrom% to $humidityTo%.")

        if(humidity < humidityFrom)
            println("Please, make it increase by ${humidityFrom - humidity}%.")
        else if(humidity > humidityTo)
            println("Please, make it decrease by ${humidity - humidityTo}%.")
        else
            println("The humidity is comfortable")
    }

}