package com.example.plugins

import DataImpl
import GetData
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/companies") {
            val data = DataImpl()
            val resData = GetData(data)
            call.respond(mapOf("companies" to resData.getList()))
        }
    }
}


val tmp = listOf(
    mapOf("Gasmyas" to "Football"),
    mapOf("Dyldy" to "Volleyball"),
    mapOf("Rykola" to "Handball"),
    mapOf("TikTak" to "Basketball"),
    mapOf("Balabol" to "Regbi")
)