package com.app.quotes_daily.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.quotes_daily.domain.model.quotes.Quotes

@Entity(tableName = "favourites")
data class FavouriteQuoteEntity(
    @PrimaryKey val id: Int,
    val quote: String?,
    val author: String?
)

fun Quotes.toEntity(): FavouriteQuoteEntity = FavouriteQuoteEntity(
    id = this.id ?: 0,
    quote = this.quote,
    author = this.author
)

fun FavouriteQuoteEntity.toDomain(): Quotes = Quotes(
    id = this.id,
    quote = this.quote,
    author = this.author
)
