package com.example.myapplication.features.shared.placecard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.Place
import com.example.myapplication.domain.use_case.PlaceUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PlaceDetailCardViewModel @Inject constructor(
    val placeUsesCases: PlaceUsesCases
) : ViewModel() {
    private val _place = mutableStateOf<Place?>(null)
    val place: State<Place?> = _place

    private val _placeIcon = mutableStateOf<String?>(null)
    val placeIcon: State<String?> = _placeIcon

    private val _placeName = mutableStateOf<String?>(null)
    val placeName: State<String?> = _placeName

    private val _placeDescription = mutableStateOf<String?>(null)
    val placeDescription: State<String?> = _placeDescription

    private val _placePhoto = mutableStateOf<String?>(null)
    val placePhoto: State<String?> = _placePhoto

    private val _placeAddress = mutableStateOf<String?>(null)
    val placeAddress: State<String?> = _placeAddress

    private val _placeRating = mutableStateOf<Float?>(null)
    val placeRating: State<Float?> = _placeRating

    private val _placePrice = mutableStateOf<Int?>(null)
    val placePrice: State<Int?> = _placePrice

    private val _isFavorite = mutableStateOf<Boolean?>(null)
    val isFavorite: State<Boolean?> = _isFavorite

    private var onCloseCardCallback: () -> Unit = {}

    private var onPlaceChangeCallback: ((Place) -> Unit) = {}

    fun setOnCloseCardCallback(onCloseCardCallback: () -> Unit) {
        this.onCloseCardCallback = onCloseCardCallback
    }

    fun setOnPlaceChangeCallback(onPlaceChangeCallback: ((Place) -> Unit)) {
        this.onPlaceChangeCallback = onPlaceChangeCallback
    }

    fun setPlace(place: Place?) {
        _place.value = place
        updateData(place)
    }

    private fun updateData(place: Place?) {
        _placeIcon.value = place?.category?.icon
        _placeName.value = place?.name
        _placeDescription.value = place?.category?.name
        _placePhoto.value = place?.photo
        _placeAddress.value = place?.location
        _placeRating.value = place?.rating
        _placePrice.value = place?.price
        _isFavorite.value = place?.isFavorite
    }

    fun onClickCancelCard() {
        onCloseCardCallback()
    }

    fun onClickFavorite() {
        viewModelScope.launch {
            _place.value?.let { place ->
                if (place.isFavorite) {
                    placeUsesCases.deleteFavoritePlace(
                        place = place
                    )
                } else {
                    placeUsesCases.addNewFavoritePlace(
                        place = place
                    )
                }
                onPlaceChangeCallback(
                    place.copy(
                        isFavorite = !place.isFavorite
                    )
                )
            }
        }
    }
}
