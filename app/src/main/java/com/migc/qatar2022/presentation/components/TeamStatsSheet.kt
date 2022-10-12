package com.migc.qatar2022.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.migc.qatar2022.R
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.presentation.screens.teams_map.OddsDetailsState
import com.migc.qatar2022.ui.theme.*

@Composable
fun TeamStatsSheet(
    countryInfo: CountryInfo,
    oddsDetails: OddsDetailsState,
    onOddClicked: (String) -> Unit
) {
    val championships: List<Int> = countryInfo.championships
    val finals: List<Int> = countryInfo.runnerUps
    Log.d("TeamStatsSheet", "init")

    val seeText = stringResource(id = R.string.see_text)
    val seeOddsText = stringResource(id = R.string.see_odds_text)
    val firstText = remember { mutableStateOf(seeText) }
    val secondText = remember { mutableStateOf(seeText) }
    val thirdText = remember { mutableStateOf(seeText) }
    val wcText = remember { mutableStateOf(seeOddsText) }

    LaunchedEffect(key1 = oddsDetails) {
        Log.d("LaunchedEffect", "oddsDetails.value $oddsDetails")
        if (oddsDetails.bettingOdds != null) {
            firstText.value = oddsDetails.bettingOdds.first.toString()
            secondText.value = oddsDetails.bettingOdds.second.toString()
            thirdText.value = oddsDetails.bettingOdds.third.toString()
            wcText.value = oddsDetails.bettingOdds.wc.toString()
        } else {
            firstText.value = seeText
            secondText.value = seeText
            thirdText.value = seeText
            wcText.value = seeOddsText
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        BottomSheetHandle()
        Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
        Text(
            modifier = Modifier.padding(horizontal = LARGE_PADDING),
            text = stringResource(id = R.string.team_app_performance_subtitle),
            color = mainColor
        )
        Divider()
        Spacer(modifier = Modifier.height(SMALL_VERTICAL_GAP))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LARGE_PADDING),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TeamStatBadge(
                countryInfo = countryInfo,
                isValueLoading = oddsDetails.isLoading,
                text = firstText.value,
                backgroundColor = goldColor,
                onClick = onOddClicked
            )
            TeamStatBadge(
                countryInfo = countryInfo,
                isValueLoading = oddsDetails.isLoading,
                text = secondText.value,
                backgroundColor = silverColor,
                onClick = onOddClicked
            )
            TeamStatBadge(
                countryInfo = countryInfo,
                isValueLoading = oddsDetails.isLoading,
                text = thirdText.value,
                backgroundColor = bronzeColor,
                onClick = onOddClicked
            )
            TeamStatBadge(
                countryInfo = countryInfo,
                isValueLoading = oddsDetails.isLoading,
                text = wcText.value,
                backgroundColor = mainColor,
                textColor = mainBackgroundColor,
                onClick = onOddClicked,
            )
        }
        Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
        Text(
            text = stringResource(id = R.string.team_facts_subtitle),
            modifier = Modifier.padding(horizontal = LARGE_PADDING),
            color = mainColor
        )
        Divider()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LARGE_PADDING),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (countryInfo.teamId.isNotEmpty()) {
                            Image(
                                painter = painterResource(id = TeamsData.flagsMap[countryInfo.teamId]!!),
                                contentDescription = stringResource(R.string.home_team_flag),
                                modifier = Modifier.size(FLAG_ROW_IMAGE_SIZE)
                            )
                        }
                        Spacer(modifier = Modifier.width(LARGE_PADDING))
                        Text(
                            text = countryInfo.teamName,
                            color = mainColor,
                            fontSize = Typography.h6.fontSize,
                            fontStyle = FontStyle.Italic
                        )
                    }

                    Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                    StarsWidget(
                        modifier = Modifier,
                        years = championships,
                        color = goldColor
                    )
                    Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                    StarsWidget(
                        modifier = Modifier,
                        years = finals,
                        color = silverColor
                    )
                }
                Column(
                    modifier = Modifier.weight(0.4f),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.End
                ) {
                    Coin(title = stringResource(id = R.string.ranking_text), value = countryInfo.ranking)
                    Spacer(modifier = Modifier.height(SMALL_VERTICAL_GAP))
                    Coin(title = stringResource(id = R.string.appearances_text), value = countryInfo.appearances)
                }
            }

        }
    }
}

@Composable
@Preview
fun TeamStatsSheetPreview() {
    TeamStatsSheet(
        countryInfo = CountryInfo(
            "URU",
            "Uruguay",
            18.34,
            12.48,
            appearances = 5,
            championships = listOf(1930, 1950),
            runnerUps = listOf(1970, 1994),
            ranking = 13
        ),
        oddsDetails = OddsDetailsState(),
        onOddClicked = {},
    )
}