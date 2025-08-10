package com.app.quotes_daily.domain.usecase.favourite

import com.app.quotes_daily.domain.model.quotes.Quotes
import com.app.quotes_daily.domain.repository.favourite.FavouriteRepository
import kotlinx.coroutines.flow.Flow

class GetFavouritesUseCase(private val repository: FavouriteRepository) {
    operator fun invoke(): Flow<List<Quotes>> = repository.getFavourites()
}
