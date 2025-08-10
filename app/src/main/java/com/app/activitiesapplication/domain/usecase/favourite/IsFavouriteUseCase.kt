package com.app.activitiesapplication.domain.usecase.favourite

import com.app.activitiesapplication.domain.repository.favourite.FavouriteRepository
import kotlinx.coroutines.flow.Flow

class IsFavouriteUseCase(private val repository: FavouriteRepository) {
    operator fun invoke(id: Int): Flow<Boolean> = repository.isFavourite(id)
}
