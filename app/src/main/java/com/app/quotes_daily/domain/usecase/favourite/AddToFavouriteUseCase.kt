package com.app.quotes_daily.domain.usecase.favourite

import com.app.quotes_daily.domain.model.quotes.Quotes
import com.app.quotes_daily.domain.repository.favourite.FavouriteRepository

class AddToFavouriteUseCase(private val repository: FavouriteRepository) {
    suspend operator fun invoke(quotes: Quotes) = repository.addToFavourite(quotes)
}
