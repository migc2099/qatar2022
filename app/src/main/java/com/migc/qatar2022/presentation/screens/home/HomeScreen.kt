package com.migc.qatar2022.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.migc.qatar2022.navigation.Screen
import com.migc.qatar2022.presentation.components.CountDownDisplay
import com.migc.qatar2022.presentation.components.DisplayWinnersButton
import com.migc.qatar2022.presentation.components.GroupCard
import com.migc.qatar2022.presentation.screens.playoffs.PlayoffDialog
import com.migc.qatar2022.presentation.screens.playoffs.PlayoffsGrid
import com.migc.qatar2022.presentation.screens.playoffs.PodiumDialog
import com.migc.qatar2022.ui.theme.*

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val teamStatsMap = homeViewModel.statsPerGroup.value
    val playoffs = homeViewModel.playoffs.collectAsState()
    val playoffCompletedState = homeViewModel.playoffCompletedState.collectAsState()

    val listState = rememberLazyListState()
    LaunchedEffect(key1 = true) {
        Log.d("HomeScreen", "LaunchEffect")
        homeViewModel.onEvent(HomeUiEvent.OnStart)
        listState.scrollToItem(homeViewModel.listPosition, homeViewModel.listOffSet)
    }

    val showPlayoffDialog = remember { mutableStateOf(false) }
    val showPodiumDialog = remember { mutableStateOf(false) }
    val selectedPlayoff = homeViewModel.selectedPlayoff.collectAsState()
    val bestTeams = homeViewModel.bestTeams.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            CountDownDisplay(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        }
        item {
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
            Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
        }
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(384.dp)
            ) {
                item { Spacer(modifier = Modifier.width(8.dp)) }
                item {
                    Card(
                        modifier = Modifier
                            .width(620.dp)
                            .height(384.dp),
                        shape = RoundedCornerShape(SMALL_ROUND_CORNER),
                        backgroundColor = mainColor
                    ) {
                        PlayoffsGrid(
                            modifier = Modifier.padding(horizontal = MEDIUM_PADDING),
                            playoffs = playoffs.value
                        ) {
                            Log.d("LazyRow", "roundKey clicked: $it")
                            homeViewModel.onEvent(HomeUiEvent.OnPlayoffDialogClicked(it))
                            showPlayoffDialog.value = true
                        }
                    }
                }
                item { Spacer(modifier = Modifier.width(8.dp)) }
            }
        }
        item {
            Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
        }
        item {
            DisplayWinnersButton(
                enabled = playoffCompletedState.value,
                onClick = {
                    showPodiumDialog.value = true
                }
            )
        }
    }

    if (showPlayoffDialog.value &&
        selectedPlayoff.value.firstTeam.isNotEmpty() && selectedPlayoff.value.secondTeam.isNotEmpty()
    ) {
        PlayoffDialog(
            playoff = selectedPlayoff.value,
            onDismiss = {
                showPlayoffDialog.value = false
            },
            onNegativeClick = {
                showPlayoffDialog.value = false
            },
            onPositiveClick = {
                homeViewModel.onEvent(HomeUiEvent.OnPlayoffDialogCompleted(it))
                showPlayoffDialog.value = false
            }
        )
    }

    if (playoffCompletedState.value && showPodiumDialog.value) {
        PodiumDialog(
            teams = bestTeams.value,
            onDismiss = { showPodiumDialog.value = false },
            onReset = { /*TODO*/ },
            onClose = { showPodiumDialog.value = false }
        )
    }

}
