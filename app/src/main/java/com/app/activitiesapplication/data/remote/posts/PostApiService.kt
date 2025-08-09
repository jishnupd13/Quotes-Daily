package com.app.activitiesapplication.data.remote.posts

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface PostApiService {
    suspend fun getPosts(): List<PostDto>
}

class PostApiServiceImpl(private val client: HttpClient) : PostApiService {
    override suspend fun getPosts(): List<PostDto> {
        return client.get("https://jsonplaceholder.typicode.com/posts").body()
    }
}
