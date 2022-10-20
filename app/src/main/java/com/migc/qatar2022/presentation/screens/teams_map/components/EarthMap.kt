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
import com.migc.qatar2022.domain.model.CountryInfo

private const val LOG_TAG = "EarthMap"

@Composable
fun EarthMap(
    countriesInfo: List<CountryInfo>,
    onCountryClicked: (CountryInfo) -> Unit
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
        countriesInfo.forEach { country ->
            Marker(
                state = MarkerState(LatLng(country.latitude, country.longitude)),
                icon = BitmapDescriptorFactory.fromBitmap(
                    Utils.getBitmap(LocalContext.current, TeamsData.flagsMap[country.teamId]!!)!!
                ),
                title = country.teamName,
                tag = country.teamId,
                onClick = {
                    onCountryClicked(country)
                    true
                }
            )
        }

    }
}