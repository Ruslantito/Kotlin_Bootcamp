package com.example.routes

import DataImpl
import GetData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.companyRouting() {
    get("/companies") {
        val data = DataImpl()
        val resData = GetData(data)
        call.respond(mapOf("companies" to resData.getList()))
    }
    get("/company/{id}") {
        try {
            val id = call.parameters["id"]?.toInt() // ?: return@get call.respondText("ID format not correct, please try again!", status=HttpStatusCode.BadRequest)
            val data = DataImpl()
            val resData = GetData(data)
            val res = resData.getDataById(id) ?: return@get call.respondText("Not found Company with id=$id", status=HttpStatusCode.NotFound)
            call.respond(mapOf("companies" to res))
        } catch (e: Exception) {
            return@get call.respondText("ID format not correct, please try again!", status=HttpStatusCode.BadRequest)
        }
    }
}

fun Application.CompanyRoutes() {
    routing {
        companyRouting()
    }
}
