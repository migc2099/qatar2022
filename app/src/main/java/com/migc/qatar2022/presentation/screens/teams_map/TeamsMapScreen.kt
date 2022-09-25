package com.migc.qatar2022.presentation.screens.teams_map

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    val countriesData = viewModel.data.collectAsState()
    val selectedCountry = remember {
        mutableStateOf(
            CountryInfo(teamId = "QAT", teamName = "Qatar", latitude = 25.3548, longitude = 51.1839, ranking = 36)
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
            TeamStatsSheet(countryInfo = selectedCountry.value)
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