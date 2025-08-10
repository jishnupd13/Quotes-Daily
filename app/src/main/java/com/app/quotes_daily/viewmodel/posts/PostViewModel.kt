package com.app.quotes_daily.viewmodel.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.quotes_daily.domain.model.posts.Post
import com.app.quotes_daily.domain.usecase.post.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PostsUiState>(PostsUiState.Loading)
    val uiState: StateFlow<PostsUiState> = _uiState.asStateFlow()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            runCatching {
                getPostsUseCase()
            }.onSuccess { posts ->
                _uiState.value = PostsUiState.Success(posts)
            }.onFailure { throwable ->
                _uiState.value = PostsUiState.Error(throwable.localizedMessage ?: "Unknown error")
            }
        }
    }

    sealed interface PostsUiState {
        data object Loading : PostsUiState
        data class Success(val posts: List<Post>) : PostsUiState
        data class Error(val message: String) : PostsUiState
    }
}