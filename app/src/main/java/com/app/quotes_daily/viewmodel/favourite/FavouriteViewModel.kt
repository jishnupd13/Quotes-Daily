package com.app.quotes_daily.viewmodel.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.quotes_daily.domain.model.quotes.Quotes
import com.app.quotes_daily.domain.usecase.favourite.GetFavouritesUseCase
import com.app.quotes_daily.domain.usecase.favourite.RemoveFromFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavouriteUiState>(FavouriteUiState.Loading)
    val uiState: StateFlow<FavouriteUiState> = _uiState.asStateFlow()

    init {
        observeFavourites()
    }

    private fun observeFavourites() {
        viewModelScope.launch {
            getFavouritesUseCase().collectLatest { list ->
                _uiState.value = FavouriteUiState.Success(list)
            }
        }
    }

    fun onRemoveFavourite(quotes: Quotes) {
        viewModelScope.launch {
            removeFromFavouriteUseCase(quotes)
        }
    }

    sealed interface FavouriteUiState {
        data object Loading : FavouriteUiState
        data class Success(val favourites: List<Quotes>) : FavouriteUiState
    }
}
