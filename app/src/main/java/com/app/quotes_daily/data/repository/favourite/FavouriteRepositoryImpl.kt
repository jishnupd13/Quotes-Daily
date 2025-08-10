package com.app.quotes_daily.data.repository.favourite

import com.app.quotes_daily.data.local.room.FavouriteDao
import com.app.quotes_daily.data.local.room.toDomain
import com.app.quotes_daily.data.local.room.toEntity
import com.app.quotes_daily.domain.model.quotes.Quotes
import com.app.quotes_daily.domain.repository.favourite.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouriteRepositoryImpl(
    private val dao: FavouriteDao
) : FavouriteRepository {
    override suspend fun addToFavourite(quotes: Quotes) {
        if (quotes.id != null) {
            dao.insertFavourite(quotes.toEntity())
        }
    }

    override suspend fun removeFromFavourite(quotes: Quotes) {
        if (quotes.id != null) {
            dao.deleteFavourite(quotes.toEntity())
        }
    }

    override fun getFavourites(): Flow<List<Quotes>> = dao.getFavourites().map { list ->
        list.map { it.toDomain() }
    }

    override fun isFavourite(id: Int): Flow<Boolean> = dao.isFavourite(id)
}
