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
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.presentation.components.EarthMap
import com.migc.qatar2022.presentation.components.TeamStatsSheet
import com.migc.qatar2022.ui.theme.mainBackgroundColor
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun TeamsMapScreen(
    viewModel: TeamsMapViewModel = hiltViewModel()
) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    val countriesData = viewModel.data.collectAsState()
    val selectedCountry = remember {
        mutableStateOf(
            CountryInfo()
        )
    }

    Log.d("TeamsMapScreen", "countriesData ${countriesData.value.size}")

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            if (selectedCountry.value.teamId.isEmpty() && sheetState.isExpanded) {
                coroutineScope.launch {
                    sheetState.collapse()
                }
            } else {
                TeamStatsSheet(countryInfo = selectedCountry.value)
            }
        },
        sheetBackgroundColor = mainBackgroundColor,
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
    ) {
        EarthMap(
            countriesInfo = countriesData.value,
            onCountryClicked = { countryInfo ->
                coroutineScope.launch {
                    selectedCountry.value = countryInfo
                    if (sheetState.isCollapsed) {
                        Log.d("coroutineScope", countryInfo.teamName)
                        sheetState.expand()
                    } else {
                        sheetState.collapse()
                        sheetState.expand()
                        Log.d("coroutineScope", countryInfo.teamName)
                    }
                }
            }
        )
    }


}