package com.migc.qatar2022.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.migc.qatar2022.navigation.Screen
import com.migc.qatar2022.presentation.components.CountDownDisplay
import com.migc.qatar2022.presentation.components.GroupCard
import com.migc.qatar2022.ui.theme.GROUP_LAZY_ROW_HEIGHT
import com.migc.qatar2022.ui.theme.MEDIUM_PADDING
import com.migc.qatar2022.ui.theme.MEDIUM_VERTICAL_GAP
import com.migc.qatar2022.ui.theme.mainColor

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val listState = rememberLazyListState()
    val teamStatsMap = homeViewModel.statsPerGroup.value

    LaunchedEffect(key1 = true) {
        Log.d("HomeScreen", "LaunchEffect")
        homeViewModel.onEvent(HomeUiEvent.OnStart)
        listState.scrollToItem(homeViewModel.listPosition, homeViewModel.listOffSet)
    }

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
                        state = listState
                    ) {
                        teamStatsMap.forEach { (group, teamStats) ->
                            Log.d("HomeScreen", "GROUP $group")
                            Log.d("HomeScreen", "TEAMSTATS $teamStats")
                            item {
                                Row(modifier = Modifier.padding(horizontal = MEDIUM_PADDING)) {
                                    GroupCard(
                                        modifier = Modifier,
                                        size = (screenWidth * 0.8f).dp,
                                        group = group,
                                        teamsStats = teamStats
                                    ) {
                                        homeViewModel.onEvent(
                                            HomeUiEvent.OnNavigateToGroupDetails(
                                                listIndex = listState.firstVisibleItemIndex,
                                                scrollOffSet = listState.firstVisibleItemScrollOffset
                                            )
                                        )
                                        navHostController.navigate(Screen.GroupDetails.route + "/$it")
                                    }
                                }
                            }
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(text = "See All")
                    }
                }
                item {
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = mainColor)
                    ) {

                    }
                }
            }
        }

    }
}
