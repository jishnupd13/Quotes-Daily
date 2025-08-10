package com.app.quotes_daily.domain.usecase.favourite

import com.app.quotes_daily.domain.repository.favourite.FavouriteRepository
import kotlinx.coroutines.flow.Flow

class IsFavouriteUseCase(private val repository: FavouriteRepository) {
    operator fun invoke(id: Int): Flow<Boolean> = repository.isFavourite(id)
}
