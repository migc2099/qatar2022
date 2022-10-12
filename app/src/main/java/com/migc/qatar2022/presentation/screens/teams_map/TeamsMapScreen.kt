package com.migc.qatar2022.presentation.screens.teams_map

import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    val countriesData = viewModel.data.collectAsState()
    val currentCountry = viewModel.countryInfo.collectAsState()
    val oddsDetails = viewModel.odds.collectAsState()

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

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            TeamStatsSheet(
                countryInfo = currentCountry.value,
                oddsDetails = oddsDetails.value,
                onOddClicked = {
                    viewModel.onEvent(TeamsMapUiEvent.OnSeeOddsClicked(it))
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