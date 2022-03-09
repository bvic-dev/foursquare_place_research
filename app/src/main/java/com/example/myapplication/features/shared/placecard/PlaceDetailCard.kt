package com.example.myapplication.features.shared.placecard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.domain.model.Place

@Composable
fun PlaceDetailCard(
    modifier: Modifier = Modifier,
    place: Place?,
    onCloseCardCallback: () -> Unit = {},
    onPlaceChangeCallback: (Place) -> Unit = {}
) {
    val placeDetailCardViewModel: PlaceDetailCardViewModel =
        hiltViewModel<PlaceDetailCardViewModel>().apply {
            setOnCloseCardCallback(onCloseCardCallback)
            setOnPlaceChangeCallback(onPlaceChangeCallback)
            setPlace(place)
        }

    if (placeDetailCardViewModel.place.value != null) {
        Card(
            elevation = 10.dp,
            modifier = modifier
                .wrapContentSize()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                CardHeader(placeDetailCardViewModel = placeDetailCardViewModel)
                Spacer(Modifier.size(10.dp))
                CardContent(placeDetailCardViewModel = placeDetailCardViewModel)
                Spacer(Modifier.size(10.dp))
                CardFooter(placeDetailCardViewModel = placeDetailCardViewModel)
            }
        }
    }
}
