package com.app.quotes_daily.domain.repository.favourite

import com.app.quotes_daily.domain.model.quotes.Quotes
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    suspend fun addToFavourite(quotes: Quotes)
    suspend fun removeFromFavourite(quotes: Quotes)
    fun getFavourites(): Flow<List<Quotes>>
    fun isFavourite(id: Int): Flow<Boolean>
}
