package com.example.myapplication.data.remote.api

import com.example.myapplication.BuildConfig
import com.example.myapplication.data.remote.dto.PlacesDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PlacesApi {
    @GET("/v3/places/search")
    suspend fun getPlaces(
        @Header("Authorization") authorization: String = BuildConfig.FOURSQUARE_AUTHORIZATION_KEY,
        @Header("Accept-Language") language: String = "fr",
        @Query("ll") longitudeLatitude: String,
        @Query("fields") fields: String = "fsq_id,rating,name,geocodes,photos,categories,distance,location,price",
        @Query("limit") limit: Int = 25,
        @Query("radius") radius: Int = 1000
    ): PlacesDto

    companion object {
        const val BASE_URL = "https://api.foursquare.com/"
    }
}
