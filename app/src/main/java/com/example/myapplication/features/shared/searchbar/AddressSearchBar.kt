package com.example.myapplication.features.shared.searchbar

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun AddressSearchBar(
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    searchCallBack: ((LatLng) -> Unit)
) {
    val addressSearchBarViewModel: AddressSearchBarViewModel =
        hiltViewModel<AddressSearchBarViewModel>().apply {
            setSearchCallBack(searchCallBack)
        }
    val view = LocalView.current

    Column(modifier = modifier) {
        SearchBarTextField(
            isEnable = isEnable,
            addressSearchBarViewModel = addressSearchBarViewModel,
            view = view
        )
        SearchBarSuggestionMenu(
            modifier = Modifier,
            addressSearchBarViewModel = addressSearchBarViewModel,
            view = view
        )
        AddressSearchErrorText(
            text = addressSearchBarViewModel.searchError.value ?: ""
        )
    }
}
