package com.migc.qatar2022.presentation.screens.group_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.presentation.components.FixtureRow
import com.migc.qatar2022.ui.theme.*

@Composable
fun GroupDetails(
    navHostController: NavHostController,
    viewModel: GroupDetailsViewModel = hiltViewModel()
) {
    val selectedGroup by viewModel.selectedGroup.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = mainBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = viewModel.currentGroup.value,
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
                selectedGroup.forEach {
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = it.date,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(1.dp))
                            FixtureRow(
                                homeTeam = TeamsData.getTeamInGroup(it.homeTeam, it.group),
                                awayTeam = TeamsData.getTeamInGroup(it.awayTeam, it.group),
                                homeTeamScore = { },
                                awayTeamScore = { }
                            )
                        }

                    }
                }

            }
        }
    }
}