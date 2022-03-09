package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.model.Geocodes
import com.example.myapplication.domain.model.Place

@Entity
data class PlaceEntity(
    @PrimaryKey val fsqId: String,
    val geocodes: Geocodes,
    val name: String,
    val rating: Float,
    val category: Category,
    val distance: Int,
    val location: String,
    val price: Int,
    val photo: String?,
    val isFavorite: Boolean = true
) {
    fun toPlace(): Place {
        return Place(
            fsqId = fsqId,
            geocodes = geocodes,
            name = name,
            rating = rating,
            category = category,
            distance = distance,
            location = location,
            price = price,
            photo = photo,
            isFavorite = isFavorite
        )
    }
}
