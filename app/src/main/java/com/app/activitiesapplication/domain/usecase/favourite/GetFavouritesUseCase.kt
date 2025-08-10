package com.app.activitiesapplication.domain.usecase.favourite

import com.app.activitiesapplication.domain.model.quotes.Quotes
import com.app.activitiesapplication.domain.repository.favourite.FavouriteRepository
import kotlinx.coroutines.flow.Flow

class GetFavouritesUseCase(private val repository: FavouriteRepository) {
    operator fun invoke(): Flow<List<Quotes>> = repository.getFavourites()
}
