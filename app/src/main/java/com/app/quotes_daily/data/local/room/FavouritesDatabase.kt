package com.app.quotes_daily.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouriteQuoteEntity::class], version = 1, exportSchema = false)
abstract class FavouritesDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao
}
