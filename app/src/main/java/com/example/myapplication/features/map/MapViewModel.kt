package com.example.myapplication.features.map

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.Resource
import com.example.myapplication.domain.model.Place
import com.example.myapplication.domain.use_case.PlaceUsesCases
import com.example.myapplication.features.shared.helper.GeocoderHelper
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class MapViewModel @Inject constructor(
    private val placeUsesCases: PlaceUsesCases,
    private val geocoderHelper: GeocoderHelper
) : ViewModel() {
    private val brest = LatLng(48.39, -4.486)

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    // region permission
    private val _isPermissionLocation = mutableStateOf(false)

    fun setPermissionLocation(permission: Boolean) {
        _isPermissionLocation.value = permission
        _mapProperties.value = _mapProperties.value.copy(isMyLocationEnabled = permission)
    }
    // endregion

    // region Camera position && last places position load && map properties
    private val _currentPlacesPosition = mutableStateOf(brest)

    private val _cameraPosition = mutableStateOf(CameraPosition.fromLatLngZoom(brest, 16f))
    val cameraPosition: State<CameraPosition> = _cameraPosition

    private val _newCameraPosition = mutableStateOf<CameraPosition?>(null)
    val newCameraPosition: State<CameraPosition?> = _newCameraPosition

    private val _mapProperties =
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    val mapProperties: State<MapProperties> = _mapProperties

    fun setCameraPosition(cameraPosition: CameraPosition) {
        _cameraPosition.value = cameraPosition
        loadPlaces(LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude))
    }
    // endregion

    private val _placesState = mutableStateOf<List<Place>>(mutableListOf())
    val placesState: State<List<Place>> = _placesState

    private val _currentPlaces = mutableStateOf<String?>(null)
    val currentPlaces: State<String?> = _currentPlaces

    private var searchJob: Job? = null

    val searchCallBack: (LatLng) -> Unit = {
        _newCameraPosition.value = CameraPosition.fromLatLngZoom(it, 16f)
        setCameraPosition(_newCameraPosition.value!!)
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

    val onCloseCardCallback: () -> Unit = {
        _currentPlaces.value = null
    }

    private fun loadPlaces(latLng: LatLng) {
        val currentLatLng = _currentPlacesPosition.value
        val distance = geocoderHelper.getDistanceFromTwoLatLng(latLng, currentLatLng)
        // Load data only if map move from more than 500m
        if (distance > 500f || distance == 0f) {
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                placeUsesCases.getPlaces(latLng = latLng)
                    .map { result ->
                        when (result) {
                            is Resource.Success -> {
                                _currentPlacesPosition.value = latLng
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
    }

    fun onClickItem(fsqId: String) {
        _currentPlaces.value = fsqId
    }

    fun onMapTypeClick(mapType: MapType) {
        _mapProperties.value = _mapProperties.value.copy(mapType = mapType)
    }

    fun onNewCameraCatch() {
        _newCameraPosition.value = null
    }

    fun isActiveCard(): Boolean {
        return _currentPlaces.value != null
    }
}
