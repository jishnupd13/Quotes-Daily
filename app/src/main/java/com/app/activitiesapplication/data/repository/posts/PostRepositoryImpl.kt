package com.app.activitiesapplication.data.repository.posts

import com.app.activitiesapplication.data.remote.posts.PostApiService
import com.app.activitiesapplication.data.remote.posts.toDomain
import com.app.activitiesapplication.domain.model.posts.Post
import com.app.activitiesapplication.domain.repository.posts.PostRepository

class PostRepositoryImpl(private val api: PostApiService) : PostRepository {
    override suspend fun getPosts(): List<Post> = api.getPosts().map { it.toDomain() }
}