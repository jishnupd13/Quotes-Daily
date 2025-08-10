package com.app.quotes_daily.data.repository.posts

import com.app.quotes_daily.data.remote.posts.PostApiService
import com.app.quotes_daily.data.remote.posts.toDomain
import com.app.quotes_daily.domain.model.posts.Post
import com.app.quotes_daily.domain.repository.posts.PostRepository

class PostRepositoryImpl(private val api: PostApiService) : PostRepository {
    override suspend fun getPosts(): List<Post> = api.getPosts().map { it.toDomain() }
}