package com.migc.qatar2022.presentation.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.migc.qatar2022.R
import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.navigation.Screen
import com.migc.qatar2022.presentation.components.*
import com.migc.qatar2022.presentation.screens.playoffs.PlayoffDialog
import com.migc.qatar2022.presentation.screens.playoffs.PlayoffsGrid
import com.migc.qatar2022.presentation.screens.playoffs.PodiumDialog
import com.migc.qatar2022.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onLoadInterstitial: (String) -> Unit,
    onShowInterstitial: () -> Unit
) {

    val mContext = LocalContext.current
    val tournamentActionState = homeViewModel.tournamentActionState.collectAsState()
    val userState = homeViewModel.userState.collectAsState()
    val teamStatsMap = homeViewModel.statsPerGroup.value
    val playoffs = homeViewModel.playoffs.collectAsState()
    val playoffCompletedState = homeViewModel.playoffCompletedState.collectAsState()
    val secondResultSubmitted = remember { mutableStateOf(false) }

    val listState = rememberLazyListState()
    LaunchedEffect(key1 = true) {
        Log.d("HomeScreen", "LaunchEffect")
        homeViewModel.onEvent(HomeUiEvent.OnStart)
        listState.scrollToItem(homeViewModel.listPosition, homeViewModel.listOffSet)
        onLoadInterstitial(Constants.AD_UNIT_SUBMIT_INTERSTITIAL_ID)
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
    val cupAlpha: Float by animateFloatAsState(
        targetValue = if (playoffCompletedState.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = LinearOutSlowInEasing
        )
    )
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

//    val screenshotState = rememberScreenshotState()
//    val screenshotClicked = remember { mutableStateOf(false) }

    BottomSheetScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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
                },
                scrollBehavior = scrollBehavior
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
                .background(mainBackgroundColor)
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
                        .height(KNOCK_OUT_GRID_HEIGHT * 1.1f)
                ) {
                    item { Spacer(modifier = Modifier.width(MEDIUM_PADDING)) }
                    item {
//                        ScreenshotBox(screenshotState = screenshotState) {
                        Card(
                            modifier = Modifier
                                .width(KNOCK_OUT_GRID_WIDTH)
                                .height(KNOCK_OUT_GRID_HEIGHT),
                            shape = RoundedCornerShape(SMALL_ROUND_CORNER),
                            backgroundColor = mainColor
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.TopCenter
                            ) {
                                PlayoffsGrid(
                                    modifier = Modifier.padding(horizontal = MEDIUM_PADDING),
                                    playoffs = playoffs.value
                                ) {
                                    Log.d("LazyRow", "roundKey clicked: $it")
                                    homeViewModel.onEvent(HomeUiEvent.OnPlayoffDialogClicked(it))
                                    showPlayoffDialog.value = true
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.cup),
                                    contentDescription = "cup",
                                    modifier = Modifier
                                        .size(FLAG_ROW_IMAGE_SIZE * 1.8f)
                                        .padding(top = LARGE_PADDING),
                                    alpha = cupAlpha
                                )
                            }
                        }
//                         }
                    }
                    item { Spacer(modifier = Modifier.width(MEDIUM_PADDING)) }
                }
            }
            item {
                Spacer(modifier = Modifier.height(BOTTOM_LIST_SPACE))
            }
        }
    }

    if (showPlayoffDialog.value &&
        selectedPlayoff.value.firstTeam.isNotEmpty() && selectedPlayoff.value.secondTeam.isNotEmpty()
    ) {
        PlayoffDialog(
            playoff = selectedPlayoff.value,
            onDismiss = {
                homeViewModel.onEvent(HomeUiEvent.OnPlayoffDialogDismissed)
                showPlayoffDialog.value = false
            },
            onNegativeClick = {
                homeViewModel.onEvent(HomeUiEvent.OnPlayoffDialogDismissed)
                showPlayoffDialog.value = false
            },
            onPositiveClick = {
                homeViewModel.onEvent(HomeUiEvent.OnPlayoffDialogCompleted(it))
                showPlayoffDialog.value = false
            }
        )
    }

    val uploadProcessingState = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        homeViewModel.uploadState.collect {
            when (it.operationState) {
                OperationState.Loading -> {
                    uploadProcessingState.value = true
                }
                OperationState.InternetChecked -> {
                    onLoadInterstitial(Constants.AD_UNIT_SUBMIT_INTERSTITIAL_ID)
                }
                OperationState.Success -> {
                    uploadProcessingState.value = false
                    Toast.makeText(mContext, mContext.getString(R.string.message_upload_completed), Toast.LENGTH_SHORT).show()
                    delay(1000L)
                    if (secondResultSubmitted.value && Random.nextBoolean()) {
                        onShowInterstitial()
                    }
                    secondResultSubmitted.value = true
                }
                OperationState.Failed -> {
                    uploadProcessingState.value = false
                    var message = mContext.getString(R.string.message_unexpected_error)
                    when (it.message) {
                        Constants.CONNECTION_EXCEPTION_ERROR_MESSAGE -> message =
                            mContext.getString(R.string.message_connection_error)
                        Constants.SIGN_IN_EXCEPTION_TRY_AGAIN_MESSAGE -> message =
                            mContext.getString(R.string.message_sign_in_unexpected_error)
                    }
                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }

    if (playoffCompletedState.value && showPodiumDialog.value) {
        onLoadInterstitial(Constants.AD_UNIT_SUBMIT_INTERSTITIAL_ID)
        PodiumDialog(
            teams = bestTeams.value,
            isUserAuthenticated = userState.value.user != null,
            lastTournamentActionType = tournamentActionState.value,
            isUploadWinnersProcessing = uploadProcessingState.value,
            onDismiss = { showPodiumDialog.value = false },
            onUploadWinners = {
                Log.d("onUploadWinners", "userState ${userState.value.user!!.uid}")
                homeViewModel.onEvent(HomeUiEvent.OnUploadWinnersClicked(it))
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
