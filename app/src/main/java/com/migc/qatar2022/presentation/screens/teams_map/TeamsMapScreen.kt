package com.migc.qatar2022.presentation.screens.teams_map

import androidx.compose.runtime.Composable
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.presentation.components.EarthMap

@Composable
fun TeamsMapScreen() {
    EarthMap(teamsLocations = TeamsData.locationMap)
}