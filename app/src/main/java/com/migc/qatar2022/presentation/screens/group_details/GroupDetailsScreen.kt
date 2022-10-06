package com.migc.qatar2022.presentation.screens.group_details

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.migc.qatar2022.common.Utils.toTimeDateString
import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.presentation.components.FixtureRow
import com.migc.qatar2022.presentation.components.GroupDetailsBottomSheetContent
import com.migc.qatar2022.presentation.components.GroupDetailsTopBar
import com.migc.qatar2022.ui.theme.*
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun GroupDetailsScreen(
    navHostController: NavHostController,
    viewModel: GroupDetailsViewModel = hiltViewModel()
) {
    val selectedGroup by viewModel.selectedGroup.collectAsState()
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
        topBar = {
            GroupDetailsTopBar(
                group = "Group ${viewModel.currentGroup.value}",
                onClick = {
                    coroutineScope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                FixtureDetails(
                    viewModel = viewModel,
                    matches = selectedGroup
                )
            }
        },
        sheetContent = {
            GroupDetailsBottomSheetContent(
                onGenerateScoresClick = {
                    viewModel.onEvent(GroupDetailUiEvent.OnGenerateScoredClicked)
                },
                onSaveClick = {
                    if (viewModel.currentGroup.value.isNotEmpty()) {
                        viewModel.onEvent(GroupDetailUiEvent.OnSaveChangesClicked)
                    }
                    navHostController.popBackStack()
                }
            )
        },
        sheetBackgroundColor = mainBackgroundColor,
        sheetPeekHeight = HANDLE_HEIGHT + (MEDIUM_PADDING * 2),
        sheetShape = RoundedCornerShape(topStart = MEDIUM_ROUND_CORNER, topEnd = MEDIUM_ROUND_CORNER),
    )
}

@Composable
fun FixtureDetails(
    viewModel: GroupDetailsViewModel,
    matches: List<Fixture>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = mainBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Log.d("FixtureDetails", "before items ${matches.size}")
        item {
            Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
        }
        items(matches) { match ->
            val homeTeamScore = remember { mutableStateOf("") }
            val awayTeamScore = remember { mutableStateOf("") }

            if (match.homeTeamScore != null) {
                homeTeamScore.value = match.homeTeamScore.toString()
            }
            if (match.awayTeamScore != null) {
                awayTeamScore.value = match.awayTeamScore.toString()
            }

            Log.d("FixtureDetails", "match -> $match")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MEDIUM_PADDING)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = mainColor,
                    text = match.date.toTimeDateString(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(1.dp))
                FixtureRow(
                    fixture = match,
                    homeTeamScoreValue = { homeTeamScore.value },
                    awayTeamScoreValue = { awayTeamScore.value },
                    homeTeamScore = { score ->
                        if (score.isNotEmpty()) {
                            viewModel.updateHomeTeamScore(match.matchNumber, score.toInt())
                        }
                    },
                    awayTeamScore = { score ->
                        if (score.isNotEmpty()) {
                            viewModel.updateAwayTeamScore(match.matchNumber, score.toInt())
                        }
                    }
                )
            }
        }
        item {
            Spacer(Modifier.height(BOTTOM_LIST_SPACE))
        }
    }
}