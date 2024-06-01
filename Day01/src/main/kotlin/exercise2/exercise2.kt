package exercise2

import kotlin.math.pow

fun main(args: Array<String>) {
    println("The grouping order is: " + args[0])
    println("Enter a number:")
    try {
        var numb = readln()
        if(args[0] == "lower") {
            numb = numb.reversed()
        }
        readNum(numb.toInt(), numb.length)
    } catch(e: Exception) {
        println(e)
    }
}

fun readNum(numb: Int, length: Int) {
    println()
    println("Result:")
    val step = 10f
    for (i in length downTo 1) {
        val tmp: Int = (numb / (step).pow(i-1)).toInt()
        if(isPrime(tmp)) println("$tmp - prime")
        else println("$tmp")
    }
}

fun isPrime(numb: Int): Boolean {
    var i = 2
    while (i < numb / 2) {
        if (numb % i == 0) return false
        i++
    }
    return true
}