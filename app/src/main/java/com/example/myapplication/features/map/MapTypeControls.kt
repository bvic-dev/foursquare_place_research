package com.example.myapplication.features.map

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.compose.MapType

@Composable
fun MapTypeControls(
    mapViewModel: MapViewModel,
    buttonEnabled: Boolean
) {
    Row(
        Modifier
            .fillMaxWidth()
            .horizontalScroll(state = ScrollState(0)),
        horizontalArrangement = Arrangement.Center
    ) {
        MapType.values()
            .filterNot {
                it.value == GoogleMap.MAP_TYPE_NONE
            }
            .forEach {
                Button(
                    enabled = buttonEnabled,
                    modifier = Modifier.padding(4.dp),
                    onClick = { mapViewModel.onMapTypeClick(it) }
                ) {
                    Text(text = it.toString(), style = MaterialTheme.typography.body1)
                }
            }
    }
}
