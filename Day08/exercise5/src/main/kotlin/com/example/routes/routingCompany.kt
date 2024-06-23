package com.example.routes

import DataImpl
import GetDataCompany
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.companyRouting() {
    get("/companies") {
        val data = DataImpl()
        val resData = GetDataCompany(data)
        call.respond(mapOf("companies" to resData.getDataList()))
    }
    get("/companies/{id}") {
        try {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respondText("ID format not correct, please try again!", status=HttpStatusCode.BadRequest)
            val data = DataImpl()
            val resData = GetDataCompany(data)
            val res = resData.getDataById(id-1) ?: return@get call.respondText("Not found Company with id=$id", status=HttpStatusCode.NotFound)
            call.respond(mapOf("companies" to res))
        } catch (e: Exception) {
            return@get call.respondText("ID format not correct, please try again!", status=HttpStatusCode.BadRequest)
        }
    }
}

fun Application.companyRoutes() {
    routing {
        companyRouting()
    }
}
