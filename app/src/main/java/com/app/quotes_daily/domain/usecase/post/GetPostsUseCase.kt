package com.app.quotes_daily.domain.usecase.post

import com.app.quotes_daily.domain.model.posts.Post
import com.app.quotes_daily.domain.repository.posts.PostRepository

class GetPostsUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): List<Post> = repository.getPosts()
}