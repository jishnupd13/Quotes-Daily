package com.app.activitiesapplication.data.remote.posts

import com.app.activitiesapplication.domain.model.posts.Post
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("body") val body: String,
)

fun PostDto.toDomain() = Post(
    id = id,
    title = title,
    body = body
)
