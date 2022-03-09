package com.example.myapplication.features.shared.placecard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.domain.model.Place

@Composable
fun CenterPlaceDetailCard(
    modifier: Modifier = Modifier,
    place: Place?,
    onCloseCardCallback: () -> Unit = {},
    onPlaceChangeCallback: (Place) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        PlaceDetailCard(
            place = place,
            onCloseCardCallback = onCloseCardCallback,
            onPlaceChangeCallback = onPlaceChangeCallback
        )
    }
}
