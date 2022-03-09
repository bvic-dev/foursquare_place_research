package com.example.myapplication.domain.model

import com.example.myapplication.data.local.entity.PlaceEntity

data class Place(
    val fsqId: String,
    val geocodes: Geocodes,
    val name: String,
    val rating: Float,
    val category: Category,
    val distance: Int,
    val location: String,
    val price: Int,
    val photo: String?,
    val isFavorite: Boolean = false
) {
    fun toPlaceEntity(): PlaceEntity {
        return PlaceEntity(
            fsqId = fsqId,
            geocodes = geocodes,
            name = name,
            rating = rating,
            category = category,
            distance = distance,
            location = location,
            price = price,
            photo = photo
        )
    }
}
