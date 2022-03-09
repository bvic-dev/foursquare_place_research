package com.example.myapplication.features.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.Resource
import com.example.myapplication.domain.model.Place
import com.example.myapplication.domain.use_case.PlaceUsesCases
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val placeUsesCases: PlaceUsesCases
) : ViewModel() {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _placesState = mutableStateOf<List<Place>>(mutableListOf())
    val placesState: State<List<Place>> = _placesState

    private val _currentPlaces = mutableStateOf<String?>(null)
    val currentPlaces: State<String?> = _currentPlaces

    private var searchJob: Job? = null
    val searchCallBack: (LatLng) -> Unit = {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            placeUsesCases.getPlaces(latLng = it)
                .map { result ->
                    when (result) {
                        is Resource.Success -> {
                            _placesState.value = result.data ?: mutableListOf()
                            _isLoading.value = false
                        }
                        is Resource.Error -> {
                            _placesState.value = mutableListOf()
                            _isLoading.value = false
                        }
                        is Resource.Loading -> {
                            _placesState.value = mutableListOf()
                            _isLoading.value = true
                        }
                    }
                }.launchIn(this)
        }
    }

    val onPlaceChangeCallback: (Place) -> Unit = { place ->
        val myIndex = _placesState.value.indexOf(
            _placesState.value.find {
                it.fsqId == place.fsqId
            }
        )
        _placesState.value = _placesState.value.toMutableList().apply {
            this[myIndex] = place
        }
    }

    val onSelectItemCallback: (String) -> Unit = { fsqId ->
        _currentPlaces.value = fsqId
    }

    val onCloseCardCallback: () -> Unit = {
        _currentPlaces.value = null
    }

    fun isActiveCard(): Boolean {
        return _currentPlaces.value != null
    }
}
