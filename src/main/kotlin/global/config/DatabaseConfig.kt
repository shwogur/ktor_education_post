package com.example.global.config

import com.example.domain.post.Post
import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

val dotenv = dotenv()

object DatabaseConfig {
    fun init(){
        Database.connect(
            url = dotenv["DB_URL"],
            driver = "com.mysql.cj.jdbc.Driver",
            user = dotenv["DB_USER"],
            password = dotenv["DB_PASSWORD"],
        )

        transaction {
            SchemaUtils.create(Post)
        }
    }
}