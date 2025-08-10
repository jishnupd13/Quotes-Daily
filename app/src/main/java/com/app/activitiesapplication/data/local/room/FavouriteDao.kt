package com.app.activitiesapplication.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(item: FavouriteQuoteEntity)

    @Delete
    suspend fun deleteFavourite(item: FavouriteQuoteEntity)

    @Query("SELECT * FROM favourites")
    fun getFavourites(): Flow<List<FavouriteQuoteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favourites WHERE id = :id)")
    fun isFavourite(id: Int): Flow<Boolean>
}
