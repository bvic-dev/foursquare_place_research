package com.example.myapplication.features.favoris

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.features.shared.Loader
import com.example.myapplication.features.shared.placecard.CenterPlaceDetailCard
import com.example.myapplication.features.shared.placelist.PlaceList

@Composable
fun FavoritesScreen() {
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()

    DisposableEffect(key1 = favoriteViewModel) {
        favoriteViewModel.onStart()
        onDispose { }
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Title()
        Spacer(modifier = Modifier.size(16.dp))
        PlaceList(
            onSelectItemCallBack = favoriteViewModel.onSelectItemCallback,
            isEnabled = !favoriteViewModel.isActiveCard(),
            places = favoriteViewModel.placesState.value,
            emptyDataSet = "No data, add some to favorite"
        )
    }
    CenterPlaceDetailCard(
        place = favoriteViewModel.currentPlaces.value?.let { fsqId ->
            favoriteViewModel.placesState.value.find { it.fsqId == fsqId }
        },
        onCloseCardCallback = favoriteViewModel.onCloseCardCallback,
        onPlaceChangeCallback = favoriteViewModel.onPlaceChangeCallback
    )
    Loader(
        isLoading = favoriteViewModel.isLoading.value
    )
}

@Composable
private fun Title() {
    Text(
        text = "My favorites",
        fontSize = MaterialTheme.typography.h4.fontSize
    )
}
