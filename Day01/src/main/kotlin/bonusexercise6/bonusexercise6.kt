package bonusexercise6

import kotlin.math.abs

var exit = false
var numberLength = 0

fun main() {
    val numbersToWords: MutableMap<Int, String> = mutableMapOf()
    initNumbersData(numbersToWords)

    println("The program is running. Enter a number or type \"exit\" to stop:")
    while (true) {
        val numb: Int = readNum()
        if (exit) {
            println()
            println("Bye!")
            break
        } else {
            convertNumberToWord(numb, numbersToWords)
            println()
            println("Enter a number:")
        }
    }
}

fun convertNumberToWord(numb: Int, numbersToWords: MutableMap<Int, String>) {
    println("Result: $numb")
    var minus = false
    if(numb < 0) minus = true
    val number = abs(numb)
    if (minus) print("minus-")
    processCount(number, numbersToWords)
    println()
}

fun processCount(number: Int, numbersToWords: MutableMap<Int, String>) {
    if(number <= 20) {
        print(numbersToWords[number])
    } else if(number in 21..99) {
        val number10 = from21till99(number, numbersToWords)
        if (number10 > 0) {
            processCount(number10, numbersToWords)
        }
    }
    else {
        var devideNumber = 10
        when (number) {
            in 100..999 -> devideNumber = 100
            in 1000..999999 -> devideNumber = 1000
            in 1000000..999999999 -> devideNumber = 1000000
            1000000000 -> devideNumber = 1000000000
        }
        val number10 = from99tillEnd(number, devideNumber, numbersToWords)
        if (number10 > 0) {
            processCount(number10, numbersToWords)
        }
    }
}

fun from21till99(number: Int, numbersToWords: MutableMap<Int, String>): Int {
    val tmpN1 = (number / 10) * 10
    print(numbersToWords[tmpN1])
    val tmpN2 = number % 10
    if(tmpN2 > 0) {
        print("-")
    }
    return tmpN2
}

fun from99tillEnd(number: Int, devideNumb: Int, numbersToWords: MutableMap<Int, String>): Int {
    val tmpN1 = (number / devideNumb)
    if(tmpN1 > 20) processCount(tmpN1, numbersToWords)
    else print(numbersToWords[tmpN1])
    print("-${numbersToWords[devideNumb]}")

    val tmpN2 = number % devideNumb
    if(tmpN2 > 0) {
        print("-")
    }
    return tmpN2
}

fun initNumbersData(numbersToWords: MutableMap<Int, String>)  {
    numbersToWords[0] = "zero"
    numbersToWords[1] = "one"
    numbersToWords[2] = "two"
    numbersToWords[3] = "three"
    numbersToWords[4] = "four"
    numbersToWords[5] = "five"
    numbersToWords[6] = "six"
    numbersToWords[7] = "seven"
    numbersToWords[8] = "eight"
    numbersToWords[9] = "nine"
    numbersToWords[10] = "ten"
    numbersToWords[11] = "eleven"
    numbersToWords[12] = "twelve"
    numbersToWords[13] = "thirteen"
    numbersToWords[14] = "fourteen"
    numbersToWords[15] = "fifteen"
    numbersToWords[16] = "sixteen"
    numbersToWords[17] = "seventeen"
    numbersToWords[18] = "eighteen"
    numbersToWords[19] = "nineteen"
    numbersToWords[20] = "twenty"

    numbersToWords[30] = "thirty"
    numbersToWords[40] = "forty"
    numbersToWords[50] = "fifty"
    numbersToWords[60] = "sixty"
    numbersToWords[70] = "seventy"
    numbersToWords[80] = "eighty"
    numbersToWords[90] = "ninety"

    numbersToWords[100] = "hundred"
    numbersToWords[1000] = "thousand"
    numbersToWords[1000000] = "million"
    numbersToWords[1000000000] = "billion"
}

fun readNum(): Int {
    val firstSymbol = readln()
    if(firstSymbol == "exit") {
        exit = true
        return 0
    }
    numberLength = firstSymbol.length
    val limitNumber = 1000000000
    var numb: Int
    try {
        numb = firstSymbol.toInt()
    } catch(e: NumberFormatException) {
        println("Incorrect format, try again")
        println()
        println("Enter a number:")
        numb = readNum()
    }
    if(numb < limitNumber * -1 || numb > limitNumber) {
        println("Number out of limits, try again")
        println()
        println("Enter a number:")
        numb = readNum()
    }
    return numb
}
