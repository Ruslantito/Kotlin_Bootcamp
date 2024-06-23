package com.example.plugins

import com.example.routes.companyRoutes
import com.example.routes.vacancyRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
    companyRoutes()
    vacancyRoutes()
}
