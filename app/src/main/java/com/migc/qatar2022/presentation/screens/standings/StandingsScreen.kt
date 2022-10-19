package com.migc.qatar2022.presentation.screens.standings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.migc.qatar2022.R
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.presentation.components.StandingListItem
import com.migc.qatar2022.ui.theme.*

@Composable
fun StandingScreen(
//    navHostController: NavHostController,
    viewModel: StandingsViewModel = hiltViewModel()
) {
    val teamStats = viewModel.teamStats.collectAsState()

    val digitsModifier = Modifier
        .padding(horizontal = SMALL_HORIZONTAL_PADDING)
        .width(24.dp)

    Column(
        modifier = Modifier
            .background(mainBackgroundColor)
            .padding(top = MEDIUM_VERTICAL_PADDING)
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            color = mainColor,
            text = stringResource(R.string.final_standings_title),
            textAlign = TextAlign.Center,
            fontSize = Typography.h5.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            Row(
                modifier = Modifier.weight(0.5f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DigitText(modifier = Modifier.width(36.dp), text = stringResource(R.string.points_acronym))
                DigitText(modifier = digitsModifier, text = stringResource(R.string.wins_acronym))
                DigitText(modifier = digitsModifier, text = stringResource(R.string.draw_acronym))
                DigitText(modifier = digitsModifier, text = stringResource(R.string.loses_acronym))
                DigitText(modifier = digitsModifier, text = stringResource(R.string.goals_difference_acronym))
            }
        }
        Spacer(modifier = Modifier.height(1.dp))
        Divider()
        LazyColumn {
            itemsIndexed(teamStats.value) { index, team ->
                StandingListItem(
                    digitsModifier = digitsModifier,
                    position = index + 1,
                    flag = TeamsData.flagsMap[team.teamId]!!,
                    teamName = stringResource(id = TeamsData.countriesMap[team.teamId]!!),
                    points = team.points.toString(),
                    wins = team.wins.toString(),
                    draws = team.draws.toString(),
                    loses = team.loses.toString(),
                    goalsDiff = (team.goalsInFavor - team.goalsAgainst).toString()
                )
            }
            item {
                Spacer(modifier = Modifier.height(BOTTOM_LIST_SPACE))
            }
        }
    }


}

@Composable
fun DigitText(modifier: Modifier, text: String) {
    Text(
        modifier = modifier,
        color = mainColor,
        text = text,
        textAlign = TextAlign.Center,
        fontSize = Typography.subtitle1.fontSize,
        fontWeight = FontWeight.Bold
    )
}