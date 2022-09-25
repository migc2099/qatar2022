package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.ui.theme.*

@Composable
fun TeamStatsSheet(countryInfo: CountryInfo) {
    val championships: List<Int> = countryInfo.championships
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LARGE_PADDING),
        contentAlignment = Alignment.Center
    ) {
        Row() {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Appearances",
                    fontSize = Typography.h5.fontSize,
                    color = mainColor
                )
                Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                Text(
                    text = "2",
                    fontSize = Typography.h3.fontSize,
                    color = mainColor
                )
            }
            Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
            if (championships.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(championships) { year ->
                        Text(
                            text = year.toString(),
                            fontSize = Typography.h6.fontSize,
                            color = mainColor
                        )
                    }
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
            championships = listOf(1930, 1950),
            finals = listOf(1930, 1950),
            ranking = 13
        )
    )
}