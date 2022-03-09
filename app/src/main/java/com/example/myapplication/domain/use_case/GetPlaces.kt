package com.example.myapplication.domain.use_case

import com.example.myapplication.core.Resource
import com.example.myapplication.domain.model.Place
import com.example.myapplication.domain.repository.PlacesRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class GetPlaces(
    private val repository: PlacesRepository
) {
    operator fun invoke(latLng: LatLng): Flow<Resource<List<Place>>> {
        return repository.getPlaces("${latLng.latitude},${latLng.longitude}")
    }
}
