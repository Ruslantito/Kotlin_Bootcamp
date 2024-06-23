package com.example.routes

import DataImpl
import GetDataVacancy
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.vacancyRouting() {
    get("/vacancies") {
        val data = DataImpl()
        val resData = GetDataVacancy(data)
        call.respond(mapOf("vacancies" to resData.getList()))
    }
}

fun Application.vacancyRoutes() {
    routing {
        vacancyRouting()
    }
}