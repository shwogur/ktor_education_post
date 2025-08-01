package com.example.domain.post

import kotlinx.serialization.Serializable

@Serializable
data class PostDTO(
    val id: Int? = null,
    val title: String,
    val content: String,
    val createdAt: String? = null
)