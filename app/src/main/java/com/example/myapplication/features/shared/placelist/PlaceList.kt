package com.example.myapplication.features.shared.placelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.domain.model.Place

@Composable
fun PlaceList(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onSelectItemCallBack: ((String) -> Unit),
    emptyDataSet: String? = null,
    places: List<Place>
) {
    val placeListViewModel: PlaceListViewModel =
        hiltViewModel<PlaceListViewModel>().apply {
            setPlaces(places)
            setOnSelectItemCallBack(onSelectItemCallBack)
        }
    val placesState = placeListViewModel.placesState.value
    if (placesState.isNotEmpty()) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            items(placesState.size) { i ->
                val place = placesState[i]
                PlaceListContet(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable(
                            enabled = isEnabled
                        ) {
                            placeListViewModel.onClickItem(place)
                        },
                    place = place
                )
                if (i < placesState.size - 1) {
                    Divider()
                }
            }
        }
    } else if (emptyDataSet != null) {
        PlaceListEmptyDataSet(emptyDataSet)
    }
}
