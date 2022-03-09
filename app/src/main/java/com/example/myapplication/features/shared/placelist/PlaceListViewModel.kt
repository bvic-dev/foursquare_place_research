package com.example.myapplication.features.shared.placelist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaceListViewModel @Inject constructor() : ViewModel() {
    private var _placesState = mutableStateOf<List<Place>>(mutableListOf())
    val placesState: State<List<Place>> = _placesState

    private var onSelectItemCallBack: ((String) -> Unit) = { }

    fun setPlaces(places: List<Place>) {
        _placesState.value = places
    }

    fun setOnSelectItemCallBack(searchCallBack: ((String) -> Unit)) {
        this.onSelectItemCallBack = searchCallBack
    }

    fun onClickItem(placeViewModel: Place) {
        onSelectItemCallBack(placeViewModel.fsqId)
    }
}
