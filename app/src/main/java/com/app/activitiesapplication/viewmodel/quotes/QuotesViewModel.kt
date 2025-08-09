package com.app.activitiesapplication.viewmodel.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.activitiesapplication.domain.model.quotes.Quotes
import com.app.activitiesapplication.domain.usecase.quotes.GetQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase
) : ViewModel() {

    init {
        fetchQuotes()
    }

    private val _uiState = MutableStateFlow<QuotesUiState>(QuotesUiState.Loading)
    val uiState: StateFlow<QuotesUiState> = _uiState.asStateFlow()

    private fun fetchQuotes() {
        viewModelScope.launch {
            runCatching {
                getQuotesUseCase()
            }.onSuccess { posts ->
                delay(2000)
                _uiState.value = QuotesUiState.Success(posts)
            }.onFailure { throwable ->
                _uiState.value = QuotesUiState.Error(throwable.localizedMessage ?: "Unknown error")
            }
        }
    }

    sealed interface QuotesUiState {
        data object Loading : QuotesUiState
        data class Success(val quotes: List<Quotes>) : QuotesUiState
        data class Error(val message: String) : QuotesUiState
    }
}