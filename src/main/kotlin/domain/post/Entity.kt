package com.example.domain.post

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.CurrentDateTime
import org.jetbrains.exposed.sql.jodatime.datetime

object Post: IntIdTable() {
    val title = varchar("title", 255)
    val content = text("content")
    val createdAt = datetime("created").defaultExpression(CurrentDateTime)
}

class PostEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PostEntity>(Post)

    var title by Post.title;
    var content by Post.content;
    var createdAt by Post.createdAt;
}