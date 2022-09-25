package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.migc.qatar2022.R
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.ui.theme.*

@Composable
fun TeamStatsSheet(countryInfo: CountryInfo) {
    val championships: List<Int> = countryInfo.championships
    val finals: List<Int> = countryInfo.runnerUps
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
                    if (countryInfo.teamId.isNotEmpty()){
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
        )
    )
}