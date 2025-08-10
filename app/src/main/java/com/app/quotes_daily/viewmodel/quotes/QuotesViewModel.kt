package com.app.quotes_daily.viewmodel.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.quotes_daily.domain.model.quotes.Quotes
import com.app.quotes_daily.domain.usecase.quotes.GetQuotesUseCase
import com.app.quotes_daily.domain.usecase.favourite.AddToFavouriteUseCase
import com.app.quotes_daily.domain.usecase.favourite.RemoveFromFavouriteUseCase
import com.app.quotes_daily.domain.usecase.favourite.IsFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase,
    private val isFavouriteUseCase: IsFavouriteUseCase
) : ViewModel() {

    init {
        fetchQuotes()
    }

    private val _uiState = MutableStateFlow<QuotesUiState>(QuotesUiState.Loading)
    val uiState: StateFlow<QuotesUiState> = _uiState.asStateFlow()

    // Holds current favourite status for each quote id
    private val _favouriteMap = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val favouriteMap: StateFlow<Map<Int, Boolean>> = _favouriteMap.asStateFlow()

    private fun fetchQuotes() {
        viewModelScope.launch {
            runCatching {
                getQuotesUseCase()
            }.onSuccess { posts ->
                delay(2000)
                _uiState.value = QuotesUiState.Success(posts)
                // Preload favourite state for each quote id
                posts.forEach { quote ->
                    quote.id?.let { id ->
                        launch {
                            isFavouriteUseCase(id).collectLatest { isFav ->
                                _favouriteMap.value = _favouriteMap.value.toMutableMap().apply { put(id, isFav) }
                            }
                        }
                    }
                }
            }.onFailure { throwable ->
                _uiState.value = QuotesUiState.Error(throwable.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun onFavouriteToggle(quotes: Quotes) {
        val id = quotes.id ?: return
        viewModelScope.launch {
            val current = favouriteMap.value[id] ?: false
            if (current) {
                removeFromFavouriteUseCase(quotes)
            } else {
                addToFavouriteUseCase(quotes)
            }
        }
    }

    sealed interface QuotesUiState {
        data object Loading : QuotesUiState
        data class Success(val quotes: List<Quotes>) : QuotesUiState
        data class Error(val message: String) : QuotesUiState
    }
}