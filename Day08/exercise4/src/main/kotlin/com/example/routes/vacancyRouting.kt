package com.example.routes

import DataImpl
import GetDataVacancy
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.vacancyRouting() {
    get("/vacancies") {
        val data = DataImpl()
        val resData = GetDataVacancy(data)
        call.respond(mapOf("vacancies" to resData.getDataList()))
    }
    get("/vacancies/{id}") {
        try {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respondText("ID format not correct, please try again!", status= HttpStatusCode.BadRequest)
            val data = DataImpl()
            val resData = GetDataVacancy(data)
            val res = resData.getDataById(id-1) ?: return@get call.respondText("Not found Vacancy with id=$id", status= HttpStatusCode.NotFound)
            call.respond(mapOf("vacancies" to res))
        } catch (e: Exception) {
            return@get call.respondText("ID format not correct, please try again!", status= HttpStatusCode.BadRequest)
        }
    }
}

fun Application.vacancyRoutes() {
    routing {
        vacancyRouting()
    }
}