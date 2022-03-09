package com.example.myapplication.domain.repository

import com.example.myapplication.core.Resource
import com.example.myapplication.data.local.FavoritePlacesDao
import com.example.myapplication.data.remote.api.PlacesApi
import com.example.myapplication.domain.model.Place
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PlacesRepositoryImpl(
    private val api: PlacesApi,
    private val dao: FavoritePlacesDao
) : PlacesRepository {
    override fun getPlaces(longitudeLatitude: String): Flow<Resource<List<Place>>> = flow {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    api.getPlaces(longitudeLatitude = longitudeLatitude).results
                        .map { it.toPlace() }
                )
            )
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "ERROR"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "ERROR"
                )
            )
        }
    }

    override fun getFavoritePlaces(): Flow<Resource<List<Place>>> = flow {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                dao.getFavoritePlaces().map {
                    it.toPlace()
                }
            )
        )
    }

    override suspend fun addNewFavoritePlace(place: Place) {
        dao.insertNewFavoritePlace(place = place.toPlaceEntity())
    }

    override suspend fun deleteFavoritePlace(place: Place) {
        dao.deleteFavoritePlace(place = place.toPlaceEntity())
    }
}
