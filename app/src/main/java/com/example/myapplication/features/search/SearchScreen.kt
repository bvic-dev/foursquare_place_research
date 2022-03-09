package com.example.myapplication.features.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.features.shared.Loader
import com.example.myapplication.features.shared.placecard.CenterPlaceDetailCard
import com.example.myapplication.features.shared.placelist.PlaceList
import com.example.myapplication.features.shared.searchbar.AddressSearchBar

@Composable
fun SearchScreen() {
    val searchViewModel: SearchViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        AddressSearchBar(
            isEnable = !searchViewModel.isActiveCard(),
            searchCallBack = searchViewModel.searchCallBack
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        PlaceList(
            onSelectItemCallBack = searchViewModel.onSelectItemCallback,
            isEnabled = !searchViewModel.isActiveCard(),
            places = searchViewModel.placesState.value
        )
    }
    CenterPlaceDetailCard(
        place = searchViewModel.currentPlaces.value?.let { fsqId ->
            searchViewModel.placesState.value.find { it.fsqId == fsqId }
        },
        onCloseCardCallback = searchViewModel.onCloseCardCallback,
        onPlaceChangeCallback = searchViewModel.onPlaceChangeCallback
    )

    Loader(
        isLoading = searchViewModel.isLoading.value
    )
}
