package com.example.myapplication.data.remote.dto

data class LocationDto(
    val address: String,
    val admin_region: String,
    val country: String,
    val cross_street: String,
    val formatted_address: String,
    val locality: String,
    val postcode: String,
    val region: String
) {
    fun toLocation(): String {
        return "$address\n$postcode $locality"
    }
}
