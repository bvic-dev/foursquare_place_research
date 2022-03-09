package com.example.myapplication.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.local.FavoritePlacesDao
import com.example.myapplication.data.local.PlaceConverters

@Database(
    entities = [PlaceEntity::class],
    version = 1
)

@TypeConverters(PlaceConverters::class)
abstract class FavoritePlacesDatabase : RoomDatabase() {
    abstract val dao: FavoritePlacesDao

    companion object {
        const val DATABASE_NAME = "favorite_places_db"
    }
}
