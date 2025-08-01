package com.example.domain.post

import org.jetbrains.exposed.sql.transactions.transaction

class PostRepository {
    fun getAll(): List<PostDTO> = transaction {
        PostEntity.all().map {
            PostDTO(it.id.value, it.title, it.content, it.createdAt.toString())
        }
    }

    fun getById(id: Int): PostDTO? = transaction {
        PostEntity.findById(id)?.let {
            PostDTO(it.id.value, it.title, it.content, it.createdAt.toString())
        }
    }

    fun create(title: String, content: String): PostDTO = transaction {
        val post = PostEntity.new {
            this.title = title
            this.content = content
            this.createdAt = org.joda.time.DateTime.now()
        }
        PostDTO(post.id.value, post.title, post.content, post.createdAt.toString())
    }

    fun update(id: Int, title: String, content: String): PostDTO? = transaction {
        val post = PostEntity.findById(id)
        if(post != null){
            post.title = title
            post.content = content
            PostDTO(post.id.value, post.title, post.content, post.createdAt.toString())
        } else{
            null
        }
    }

    fun delete(id: Int): Boolean = transaction {
        val post = PostEntity.findById(id)
        if (post != null) {
            post.delete()
            true
        } else {
            false
        }
    }
}