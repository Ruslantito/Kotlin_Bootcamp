package com.example.routes

import CandidateInfo
import DataImpl
import GetDataResume
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.resumeRouting() {
    get("/resumes") {
        val data = DataImpl()
        val resData = GetDataResume(data)
        call.respond(mapOf("resumes" to resData.getDataList()))
    }
    post("/resumes") {
        val resume = call.receive<CandidateInfo>()
        call.respond(mapOf("resumes" to resume))
    }
}

fun Application.resumeRoutes() {
    routing {
        resumeRouting()
    }
}
