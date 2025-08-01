package com.example

import com.example.global.config.DatabaseConfig
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.configSerialization() {
    install(ContentNegotiation) {
        json()
    }
}

fun Application.module() {
    DatabaseConfig.init()

    configSerialization()
    configureRouting()
}
