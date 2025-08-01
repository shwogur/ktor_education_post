package com.example

import com.example.domain.post.PostRepository
import com.example.domain.post.postRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<IllegalStateException> { call, cause ->
            call.respondText("App in illegal state as ${cause.message}", status = HttpStatusCode.BadRequest)
        }
    }

    routing {
        staticResources(remotePath = "/content", basePackage = "public")

        get("/") {
            call.respondText("Hello World!")
        }

        get("/error") {
            throw IllegalArgumentException("그냥 에러냄")
        }
        postRoutes(PostRepository())
    }
}

