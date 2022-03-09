package com.example.myapplication.domain.use_case

import com.example.myapplication.domain.model.Place
import com.example.myapplication.domain.repository.PlacesRepository

class AddNewFavoritePlace(
    private val repository: PlacesRepository
) {
    suspend operator fun invoke(place: Place) {
        repository.addNewFavoritePlace(place = place)
    }
}
