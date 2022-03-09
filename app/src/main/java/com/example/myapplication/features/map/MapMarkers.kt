package com.example.myapplication.features.map

import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker

@Composable
fun MapMarkers(mapViewModel: MapViewModel) {
    val placeItems = mapViewModel.placesState.value
    repeat(placeItems.size) {
        Marker(
            position = LatLng(
                placeItems[it].geocodes.latitude,
                placeItems[it].geocodes.longitude
            ),
            title = placeItems[it].name,
            snippet = "click for detail",
            onInfoWindowClick = { _ ->
                mapViewModel.onClickItem(placeItems[it].fsqId)
            },
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)
        )
    }
}
