package com.migc.qatar2022.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.common.Utils

private const val LOG_TAG = "EarthMap"

@Composable
fun EarthMap(
    teamsLocations: Map<String, LatLng>
) {
    val mapUiSettings = MapUiSettings(
        compassEnabled = false,
        indoorLevelPickerEnabled = false,
        mapToolbarEnabled = false,
        myLocationButtonEnabled = false,
        rotationGesturesEnabled = false,
        scrollGesturesEnabled = true,
        scrollGesturesEnabledDuringRotateOrZoom = false,
        tiltGesturesEnabled = false,
        zoomControlsEnabled = true,
        zoomGesturesEnabled = true,
    )

    val mCurrentLatLng = remember {
        mutableStateOf(LatLng(25.3548, 51.1839))
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(mCurrentLatLng.value, 1f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
        onMapClick = {
            Log.d(LOG_TAG, "lastLatLng.value= $it")
        },
        onMapLoaded = {
            Log.d(LOG_TAG, "onMapLoaded")
        }
    ) {
        teamsLocations.forEach { map ->
            Marker(
                state = MarkerState(map.value),
                icon = BitmapDescriptorFactory.fromBitmap(
                    Utils.getBitmap(LocalContext.current, TeamsData.flagsMap[map.key]!!)!!
                ),
                title = TeamsData.countriesMap[map.key]
            )
        }

    }
}