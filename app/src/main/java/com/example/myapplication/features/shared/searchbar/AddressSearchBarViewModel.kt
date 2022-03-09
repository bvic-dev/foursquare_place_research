package com.example.myapplication.features.shared.searchbar

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.shared.helper.GeocoderHelper
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@HiltViewModel
class AddressSearchBarViewModel @Inject constructor(
    private val geocoderHelper: GeocoderHelper
) : ViewModel() {
    private val _searchQuery = mutableStateOf<String?>(null)
    val searchQuery: State<String?> = _searchQuery

    private val _searchError = mutableStateOf<String?>(null)
    val searchError: State<String?> = _searchError

    private val _suggestionList = mutableStateOf<List<String?>?>(mutableListOf())
    val suggestionList: State<List<String?>?> = _suggestionList

    private var searchCallBack: ((LatLng) -> Unit) = { }

    private var suggestionSearchJob: Job? = null

    fun setSearchCallBack(searchCallBack: ((LatLng) -> Unit)) {
        this.searchCallBack = searchCallBack
    }

    private fun addressNotFound() {
        _searchError.value = "Address not found"
    }

    fun onClearSearch() {
        _searchQuery.value = ""
        _searchError.value = null
        _suggestionList.value = null
    }

    fun onClearSuggestion() {
        _suggestionList.value = null
    }

    fun onSearch() {
        val searchQuery = _searchQuery.value
        if (!searchQuery.isNullOrBlank()) {
            geocoderHelper.getLatitudeLongitudeFromAddress(address = searchQuery)
                ?.let { latLng ->
                    searchCallBack(latLng)
                } ?: addressNotFound()
        }
    }

    fun onClickSuggestion(suggestion: String?) {
        _searchQuery.value = suggestion
        onClearSuggestion()
        onSearch()
    }

    fun onSearchValueChanged(value: String?) {
        _searchQuery.value = value
        _searchError.value = null
        value?.let {
            suggestionSearchJob?.cancel()
            suggestionSearchJob = viewModelScope.launch(Dispatchers.IO) {
                _suggestionList.value = geocoderHelper.getSuggestedAddressesFromString(text = it)
            }
        }
    }
}
