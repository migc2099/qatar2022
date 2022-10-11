package com.migc.qatar2022.presentation.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.migc.qatar2022.R
import com.migc.qatar2022.navigation.Screen
import com.migc.qatar2022.presentation.components.*
import com.migc.qatar2022.presentation.screens.playoffs.PlayoffDialog
import com.migc.qatar2022.presentation.screens.playoffs.PlayoffsGrid
import com.migc.qatar2022.presentation.screens.playoffs.PodiumDialog
import com.migc.qatar2022.ui.theme.*
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val mContext = LocalContext.current
    val tournamentActionState = homeViewModel.tournamentActionState.collectAsState()
    val userState = homeViewModel.userState.collectAsState()
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
//    val screenshotState = rememberScreenshotState()
//    val screenshotClicked = remember { mutableStateOf(false) }

    var gridHeight = 384.dp
    var gridWidth = 620.dp

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeTopBar(
                onSignInClick = {
                    navHostController.navigate(Screen.Login.route)
                },
                onMenuClick = {
                    coroutineScope.launch {
                        if (sheetState.isCollapsed) {
                            Log.d("coroutineScope", "playoffs expand")
                            sheetState.expand()
                        } else {
                            Log.d("coroutineScope", "playoffs collapse")
                            sheetState.collapse()
                        }
                    }
                }
            )
        },
        sheetContent = {
            HomeBottomSheetContent(
                isResetEnabled = playoffs.value.isNotEmpty(),
                isShowWinnersEnabled = playoffCompletedState.value,
                isFinalStandingsEnabled = playoffCompletedState.value,
                playoffCompletedState = playoffCompletedState.value,
                onResetPlayoffClick = {
                    homeViewModel.onEvent(HomeUiEvent.OnResetPlayoffsClicked)
                    coroutineScope.launch {
                        if (sheetState.isCollapsed) {
                            Log.d("coroutineScope", "playoffs expand")
                            sheetState.expand()
                        } else {
                            Log.d("coroutineScope", "playoffs collapse")
                            sheetState.collapse()
                        }
                    }
                },
                onShowWinnersClick = {
                    showPodiumDialog.value = true
                },
                onFinalStandingsClick = {
                    navHostController.navigate(Screen.Standings.route)
                },
//                onSharePlayoffGridClick = {
//                    screenshotState.capture()
//                    screenshotClicked.value = true
//                },
                onTeamsMapClick = {
                    navHostController.navigate(Screen.TeamsMapScreen.route)
                }
            )
        },
        sheetBackgroundColor = mainBackgroundColor,
        sheetPeekHeight = BOTTOM_SHEET_HEIGHT,
        sheetShape = RoundedCornerShape(topStart = MEDIUM_ROUND_CORNER, topEnd = MEDIUM_ROUND_CORNER),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                CountDownDisplay(modifier = Modifier.padding(horizontal = LARGE_PADDING, vertical = MEDIUM_PADDING))
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
                    item {
                        Spacer(modifier = Modifier.width(LARGE_PADDING))
                    }
                    teamStatsMap.forEach { (group, teamStats) ->
                        Log.d("HomeScreen", "GROUP $group")
                        Log.d("HomeScreen", "TEAMSTATS $teamStats")
                        item {
                            Row(modifier = Modifier.padding(horizontal = MEDIUM_PADDING)) {
                                GroupCard(
                                    modifier = Modifier,
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
                    item {
                        Spacer(modifier = Modifier.width(LARGE_PADDING))
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
                    item { Spacer(modifier = Modifier.width(MEDIUM_PADDING)) }
                    item {
//                        ScreenshotBox(screenshotState = screenshotState) {
                        Card(
                            modifier = Modifier
                                .width(gridWidth)
                                .height(gridHeight),
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
//                                }
                            }
                        }
                    }
                    item { Spacer(modifier = Modifier.width(MEDIUM_PADDING)) }
                }
            }
            item {
                Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
            }
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

    val uploadProcessingState = remember { mutableStateOf(false)}
    LaunchedEffect(Unit) {
        homeViewModel.uploadState.collect {
            when (it.operationState) {
                OperationState.Loading -> {
                    uploadProcessingState.value = true
                }
                OperationState.Success -> {
                    uploadProcessingState.value = false
                    Toast.makeText(mContext, it.message, Toast.LENGTH_SHORT).show()
                }
                OperationState.Failed -> {
                    uploadProcessingState.value = false
                    Toast.makeText(mContext, it.message, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }

    if (playoffCompletedState.value && showPodiumDialog.value) {
        PodiumDialog(
            teams = bestTeams.value,
            isUserAuthenticated = userState.value.user != null,
            lastTournamentActionType = tournamentActionState.value,
            isUploadWinnersProcessing = uploadProcessingState.value,
            onDismiss = { showPodiumDialog.value = false },
            onUploadWinners = {
                Log.d("onUploadWinners", "userState ${userState.value.user}")
                Log.d("onUploadWinners", "userState ${userState.value.user!!.uid}")
                if (userState.value.user != null) {
                    Log.d("onUploadWinners", "tournamentActionState ${tournamentActionState.value}")
                    if (tournamentActionState.value == TournamentActionType.FinalsFinished) {
                        Log.d("onUploadWinners", "true")
                        homeViewModel.onEvent(HomeUiEvent.OnUploadWinnersClicked(it))
                    } else {
                        Log.d("onUploadWinners", "false")
                        Toast.makeText(mContext, mContext.getString(R.string.reupload_prevention_message), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            },
            onStartOver = {
                showPodiumDialog.value = false
                homeViewModel.resetTournament()
            },
            onClose = { showPodiumDialog.value = false }
        )
    }

//    if (screenshotClicked.value) {
//        screenshotState.imageBitmap?.let {
//
//            LazyRow() {
//                item {
//                    Image(
//                        modifier = Modifier
//                            .size(256.dp),
//                        bitmap = it,
//                        contentDescription = ""
//                    )
//                }
//            }
//        }
//    }

}
