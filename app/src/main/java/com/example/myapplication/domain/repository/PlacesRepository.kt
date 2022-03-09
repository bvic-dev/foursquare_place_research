package com.example.myapplication.domain.repository

import com.example.myapplication.core.Resource
import com.example.myapplication.domain.model.Place
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    fun getPlaces(longitudeLatitude: String): Flow<Resource<List<Place>>>
    fun getFavoritePlaces(): Flow<Resource<List<Place>>>
    suspend fun addNewFavoritePlace(place: Place)
    suspend fun deleteFavoritePlace(place: Place)
}
