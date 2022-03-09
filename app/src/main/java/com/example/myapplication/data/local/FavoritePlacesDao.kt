package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.local.entity.PlaceEntity

@Dao
interface FavoritePlacesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewFavoritePlace(place: PlaceEntity)

    @Delete
    suspend fun deleteFavoritePlace(place: PlaceEntity)

    @Query("SELECT * FROM placeentity")
    suspend fun getFavoritePlaces(): List<PlaceEntity>
}
