package com.example.myapplication.domain.use_case

data class PlaceUsesCases(
    val getPlaces: GetPlaces,
    val getFavoritePlaces: GetFavoritePlaces,
    val addNewFavoritePlace: AddNewFavoritePlace,
    val deleteFavoritePlace: DeleteFavoritePlace
)
