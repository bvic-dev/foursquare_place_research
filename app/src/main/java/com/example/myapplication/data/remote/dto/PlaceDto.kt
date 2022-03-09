package com.example.myapplication.data.remote.dto

import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.model.Place

data class PlaceDto(
    val fsq_id: String,
    val categories: List<CategoryDto>,
    val distance: Int,
    val geocodes: GeocodesDto,
    val location: LocationDto,
    val name: String,
    val photos: List<PhotoDto>,
    val price: Int,
    val rating: Float
) {
    fun toPlace(): Place {
        return Place(
            fsqId = fsq_id,
            geocodes = geocodes.toGeocodes(),
            name = name,
            rating = rating / 2,
            category = if (categories.isNotEmpty()) categories[0].toCategory() else Category(
                "",
                "undefined"
            ),
            distance = distance,
            location = location.toLocation(),
            price = price,
            photo = if (photos.isNotEmpty()) photos[0].getUri() else null
        )
    }
}
