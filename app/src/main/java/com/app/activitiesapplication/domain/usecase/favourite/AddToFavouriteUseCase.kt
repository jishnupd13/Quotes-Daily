package com.app.activitiesapplication.domain.usecase.favourite

import com.app.activitiesapplication.domain.model.quotes.Quotes
import com.app.activitiesapplication.domain.repository.favourite.FavouriteRepository

class AddToFavouriteUseCase(private val repository: FavouriteRepository) {
    suspend operator fun invoke(quotes: Quotes) = repository.addToFavourite(quotes)
}
