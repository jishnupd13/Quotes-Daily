package com.app.quotes_daily.domain.repository.posts

import com.app.quotes_daily.domain.model.posts.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}