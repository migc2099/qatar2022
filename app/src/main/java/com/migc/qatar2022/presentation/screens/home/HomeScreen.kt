package com.migc.qatar2022.presentation.screens.home

import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.migc.qatar2022.R
import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.common.Constants.GOOGLE_PLAY_STORE_LINK
import com.migc.qatar2022.common.Constants.INSTAGRAM_ID
import com.migc.qatar2022.common.Utils
import com.migc.qatar2022.common.Utils.copyToClipboard
import com.migc.qatar2022.navigation.Screen
import com.migc.qatar2022.presentation.components.*
import com.migc.qatar2022.presentation.screens.home.components.HomeTopBar
import com.migc.qatar2022.presentation.screens.playoffs.PlayoffDialog
import com.migc.qatar2022.presentation.screens.playoffs.PlayoffsGrid
import com.migc.qatar2022.presentation.screens.playoffs.PodiumDialog
import com.migc.qatar2022.ui.theme.*
import com.smarttoolfactory.screenshot.ScreenshotBox
import com.smarttoolfactory.screenshot.rememberScreenshotState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onLoadInterstitial: (String) -> Unit,
    onShowInterstitial: () -> Unit
) {

    val mConfiguration = LocalConfiguration.current
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

    val expanded = mConfiguration.screenWidthDp.dp > KNOCK_OUT_GRID_WIDTH + SHARE_BUTTON_SIZE
            || mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val screenshotState = rememberScreenshotState()
    val screenshotClicked = remember { mutableStateOf(false) }

    BottomSheetScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        scaffoldState = scaffoldState,
        topBar = {
            HomeTopBar(
                onSignInClick = {
                    navHostController.navigate(Screen.Login.route)
                },
                onShareClick = {
                    screenshotState.capture()
                    screenshotState.bitmap?.let {
                        val contentUri = Utils.getTempUriFromBitmap(mContext, it)
                        if (contentUri != null) {
                            val shareIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, GOOGLE_PLAY_STORE_LINK)
                                putExtra(Intent.EXTRA_STREAM, contentUri)
                                type = "image/jpeg"
                                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            }
                            mContext.startActivity(
                                Intent.createChooser(
                                    shareIntent,
                                    mContext.getString(R.string.choose_app_share_text)
                                )
                            )
                        }
                    }
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
                .background(mainBackgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
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
                        .height(KNOCK_OUT_GRID_HEIGHT * 1.1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    item { Spacer(modifier = Modifier.width(MEDIUM_PADDING)) }
                    item {
                        Row(
                            verticalAlignment = Alignment.Bottom
                        ) {
                            ScreenshotBox(screenshotState = screenshotState) {
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
                                        Column(
                                            modifier = Modifier
                                                .width(FLAG_ROW_IMAGE_SIZE * 2f)
                                                .height(KNOCK_OUT_GRID_HEIGHT / 2.8f),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Top
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.cup),
                                                contentDescription = "cup",
                                                modifier = Modifier
                                                    .fillMaxSize(0.7f)
                                                    .padding(top = LARGE_PADDING),
                                                alpha = cupAlpha
                                            )
                                            if (playoffCompletedState.value && bestTeams.value.isNotEmpty()) {
                                                bestTeams.value[0].let {
                                                    if (it.teamId.isNotEmpty()) {
                                                        Box(
                                                            modifier = Modifier
                                                                .size(FLAG_ROW_IMAGE_SIZE * 1.2f)
                                                        ) {
                                                            TeamFlag(teamId = bestTeams.value[0].teamId, { })
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (expanded) {
                                Spacer(modifier = Modifier.width(MEDIUM_PADDING))
                                Button(
                                    onClick = {
                                        screenshotState.capture()
                                        screenshotState.bitmap?.let {
                                            val contentUri = Utils.getTempUriFromBitmap(mContext, it)
                                            if (contentUri != null) {
                                                val shareIntent: Intent = Intent().apply {
                                                    action = Intent.ACTION_SEND
                                                    putExtra(Intent.EXTRA_TEXT, GOOGLE_PLAY_STORE_LINK)
                                                    putExtra(Intent.EXTRA_STREAM, contentUri)
                                                    type = "image/jpeg"
                                                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                                }
                                                mContext.startActivity(
                                                    Intent.createChooser(
                                                        shareIntent,
                                                        mContext.getString(R.string.choose_app_share_text)
                                                    )
                                                )
                                            }
                                        }
                                    },
                                    modifier = Modifier.size(SHARE_BUTTON_SIZE),
                                    shape = CircleShape
                                ) {
                                    Icon(
                                        Icons.Filled.Share,
                                        contentDescription = stringResource(id = R.string.share_text),
                                        tint = mainBackgroundColor
                                    )
                                }
                            }
                        }
                    }
                    item { Spacer(modifier = Modifier.width(MEDIUM_PADDING)) }
                }
            }
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MEDIUM_PADDING),
                        color = mainColor,
                        text = stringResource(id = R.string.follow_me_ig),
                        textAlign = TextAlign.Center,
                        fontSize = Typography.caption.fontSize,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(SMALL_HORIZONTAL_PADDING))
                    Row(
                        modifier = Modifier
                            .size(SIGNATURE_WIDTH, SIGNATURE_HEIGHT)
                            .padding(LARGE_PADDING)
                            .clickable {
                                INSTAGRAM_ID.copyToClipboard(context = mContext)
                                Toast.makeText(mContext, mContext.getString(R.string.copied_text), Toast.LENGTH_SHORT).show()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            color = mainColor,
                            text = "@${INSTAGRAM_ID}",
                            fontSize = Typography.subtitle2.fontSize,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(SMALL_HORIZONTAL_PADDING))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_copy),
                            contentDescription = stringResource(id = R.string.copy_text),
                            tint = mainColor
                        )
                    }

                    Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                }

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
                    onShowInterstitial()
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

}
