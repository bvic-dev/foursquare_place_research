package com.example.myapplication.domain.use_case

import com.example.myapplication.core.Resource
import com.example.myapplication.domain.model.Place
import com.example.myapplication.domain.repository.PlacesRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritePlaces(
    private val repository: PlacesRepository
) {
    operator fun invoke(): Flow<Resource<List<Place>>> {
        return repository.getFavoritePlaces()
    }
}
