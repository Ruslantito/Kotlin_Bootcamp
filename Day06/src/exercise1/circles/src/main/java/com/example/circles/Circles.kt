package com.example.circles

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


class Circles {

    fun main() {

        println("Input x1:")
        val x1: Float = readNum()
        println("Input y1:")
        val y1: Float = readNum()
        println("Input r1:")
        val r1: Float = readNum()

        println("Input x2:")
        val x2: Float = readNum()
        println("Input y2:")
        val y2: Float = readNum()
        println("Input r2:")
        val r2: Float = readNum()

        val d: Float = sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
        if(d == 0.0f && r1 == r2) {
            println("Result: the circles coincide")
        } else if(d > r1 + r2) {
            println("Result: the circles do not intersect")
        } else if(d < abs(r1 - r2)) {
            println("Result: the circle inside another circle")
        } else if(d == r1 + r2 || d == abs(r1 - r2)) {
            println("Result: the circles touch")
        } else {
            println("Result: the circles intersect")
        }

    }

    private fun readNum(): Float {
        var numb: Float
        try {
            numb = readln().toFloat()
        } catch(e: NumberFormatException) {
            println("Couldn't parse a number. Please, try again")
            numb = readNum()
        }
        return numb
    }

}

