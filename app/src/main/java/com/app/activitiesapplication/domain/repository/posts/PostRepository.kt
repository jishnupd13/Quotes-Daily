package com.app.activitiesapplication.domain.repository.posts

import com.app.activitiesapplication.domain.model.posts.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}