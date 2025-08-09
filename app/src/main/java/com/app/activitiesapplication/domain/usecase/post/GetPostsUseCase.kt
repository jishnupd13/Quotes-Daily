package com.app.activitiesapplication.domain.usecase.post

import com.app.activitiesapplication.domain.model.posts.Post
import com.app.activitiesapplication.domain.repository.posts.PostRepository

class GetPostsUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): List<Post> = repository.getPosts()
}