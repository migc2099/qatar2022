package com.migc.qatar2022.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.migc.qatar2022.presentation.components.CountDownDisplay
import com.migc.qatar2022.presentation.components.GroupCard
import com.migc.qatar2022.ui.theme.GROUP_LAZY_ROW_HEIGHT
import com.migc.qatar2022.ui.theme.MEDIUM_PADDING
import com.migc.qatar2022.ui.theme.MEDIUM_VERTICAL_GAP

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    CountDownDisplay(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                    Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                }
                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(GROUP_LAZY_ROW_HEIGHT),
                    ) {
                        homeViewModel.groups.forEach {
                            item {
                                Row(modifier = Modifier.padding(horizontal = MEDIUM_PADDING)) {
                                    GroupCard(
                                        modifier = Modifier,
                                        size = (screenWidth * 0.8f).dp,
                                        group = it.name,
                                        teams = it.teams
                                    )
                                }

                            }
                        }

                    }
                }
            }
        }

    }
}
