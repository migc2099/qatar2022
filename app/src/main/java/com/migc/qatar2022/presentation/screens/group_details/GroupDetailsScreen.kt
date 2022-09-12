package com.migc.qatar2022.presentation.screens.group_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.migc.qatar2022.R
import com.migc.qatar2022.common.Utils.toTimeDateString
import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.presentation.components.FixtureRow
import com.migc.qatar2022.ui.theme.*

@Composable
fun GroupDetailsScreen(
    navHostController: NavHostController,
    viewModel: GroupDetailsViewModel = hiltViewModel()
) {
    val selectedGroup by viewModel.selectedGroup.collectAsState()
    Scaffold(
        topBar = {},
        content = {
            FixtureDetails(
                viewModel = viewModel,
                selectedGroup = selectedGroup
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = stringResource(R.string.save_changes_text))
                },
                onClick = {
                    if (viewModel.currentGroup.value.isNotEmpty()) {
                        viewModel.onSaveButtonClicked(viewModel.currentGroup.value)
                    }
                    navHostController.popBackStack()
                },
                backgroundColor = mainColor,
                contentColor = mainBackgroundColor
            )
        }
    )
}

@Composable
fun FixtureDetails(
    viewModel: GroupDetailsViewModel,
    selectedGroup: List<Fixture>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = mainBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Group ${viewModel.currentGroup.value}",
            textAlign = TextAlign.Center,
            fontSize = Typography.h4.fontSize
        )
        Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = LARGE_PADDING)
        ) {
            LazyColumn() {
                selectedGroup.forEach { match ->
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = match.date.toTimeDateString(),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(1.dp))
                            FixtureRow(
                                fixture = match,
                                homeTeamScoreValue = if (match.homeTeamScore == null) "" else match.homeTeamScore.toString(),
                                awayTeamScoreValue = if (match.awayTeamScore == null) "" else match.awayTeamScore.toString(),
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
                }

            }
        }
    }
}