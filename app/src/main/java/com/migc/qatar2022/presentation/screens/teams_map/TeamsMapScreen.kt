package com.migc.qatar2022.presentation.screens.teams_map

import android.content.pm.ActivityInfo
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.migc.qatar2022.R
import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.common.Utils.LockScreenOrientation
import com.migc.qatar2022.presentation.components.EarthMap
import com.migc.qatar2022.presentation.components.TeamStatsSheet
import com.migc.qatar2022.ui.theme.mainBackgroundColor

@ExperimentalMaterialApi
@Composable
fun TeamsMapScreen(
    viewModel: TeamsMapViewModel = hiltViewModel()
) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    val mContext = LocalContext.current
    val countriesData = viewModel.data.collectAsState()
    val currentCountry = viewModel.countryInfo.collectAsState()
    val predictions = viewModel.predictions.collectAsState()

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    LaunchedEffect(key1 = currentCountry.value) {
        Log.d("LaunchedEffect", currentCountry.value.teamName)
        if (sheetState.isCollapsed) {
            sheetState.expand()
        } else {
            if (currentCountry.value.teamId.isEmpty()) {
                sheetState.collapse()
            }
        }
    }

    LaunchedEffect(key1 = predictions.value){
        Log.d("TeamsMapScreen", "LaunchEffect predictionsState.value: ${predictions.value}")
        if (predictions.value.error.isNotEmpty()){
            var message = ""
            when (predictions.value.error) {
                Constants.SIGN_IN_REQUIRED_MESSAGE -> message = mContext.getString(R.string.message_sign_in_required)
                Constants.CONNECTION_EXCEPTION_ERROR_MESSAGE -> message = mContext.getString(R.string.message_connection_error)
                Constants.UNEXPECTED_EXCEPTION_ERROR_MESSAGE -> message = mContext.getString(R.string.message_unexpected_error)
                Constants.GETTING_DATA_ERROR_MESSAGE -> message = mContext.getString(R.string.message_retrieving_data_error)
            }
            if (message.isNotEmpty()) {
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            TeamStatsSheet(
                countryInfo = currentCountry.value,
                predictionsState = predictions.value,
                onPredictionsClicked = {
                    viewModel.onEvent(TeamsMapUiEvent.OnSeePredictionsClicked(it))
                }
            )
        },
        sheetBackgroundColor = mainBackgroundColor,
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
    ) {
        EarthMap(
            countriesInfo = countriesData.value,
            onCountryClicked = {
                viewModel.onEvent(TeamsMapUiEvent.OnCountryFlagClicked(it))
            }
        )
    }

}