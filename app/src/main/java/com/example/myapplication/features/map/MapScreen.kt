package com.example.myapplication.features.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.features.shared.Loader
import com.example.myapplication.features.shared.placecard.CenterPlaceDetailCard
import com.example.myapplication.features.shared.searchbar.AddressSearchBar
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapsScreen() {
    val mapViewModel: MapViewModel = hiltViewModel()
    val context = LocalContext.current

    val cameraPositionState = rememberCameraPositionState {
        position = mapViewModel.cameraPosition.value
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            mapViewModel.setPermissionLocation(true)
        } else {
            mapViewModel.setPermissionLocation(false)
        }
    }

    LaunchedEffect(mapViewModel) {
        snapshotFlow { cameraPositionState.position }
            .collect {
                mapViewModel.setCameraPosition(it)
            }
    }
    LaunchedEffect(mapViewModel) {
        snapshotFlow { mapViewModel.newCameraPosition.value }
            .collect {
                if (it != null) {
                    cameraPositionState.position = it
                    mapViewModel.onNewCameraCatch()
                }
            }
    }

    GoogleMap(
        cameraPositionState = cameraPositionState,
        properties = mapViewModel.mapProperties.value,
        onMapLoaded = {
            // Show permission after map load
            checkPermission(
                context = context,
                mapViewModel = mapViewModel,
                launcher = launcher
            )
        }
    ) {
        MapMarkers(mapViewModel = mapViewModel)
    }

    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 70.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AddressSearchBar(
            isEnable = !mapViewModel.isActiveCard(),
            searchCallBack = mapViewModel.searchCallBack
        )
        MapTypeControls(
            mapViewModel = mapViewModel,
            buttonEnabled = !mapViewModel.isActiveCard()
        )
    }

    CenterPlaceDetailCard(
        place = mapViewModel.currentPlaces.value?.let { fsqId ->
            mapViewModel.placesState.value.find { it.fsqId == fsqId }
        },
        onCloseCardCallback = mapViewModel.onCloseCardCallback,
        onPlaceChangeCallback = mapViewModel.onPlaceChangeCallback
    )
    Loader(isLoading = mapViewModel.isLoading.value)
}

private fun checkPermission(
    context: Context,
    mapViewModel: MapViewModel,
    launcher: ManagedActivityResultLauncher<String, Boolean>
) {
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) -> {
            mapViewModel.setPermissionLocation(true)
        }
        else -> {
            mapViewModel.setPermissionLocation(false)
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}
