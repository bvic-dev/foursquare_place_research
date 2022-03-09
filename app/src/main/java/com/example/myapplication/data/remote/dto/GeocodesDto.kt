package com.example.myapplication.data.remote.dto

import com.example.myapplication.domain.model.Geocodes

data class GeocodesDto(
    val main: MainDto
) {
    fun toGeocodes(): Geocodes {
        return Geocodes(
            latitude = main.latitude,
            longitude = main.longitude
        )
    }
}
