package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.migc.qatar2022.R
import com.migc.qatar2022.ui.theme.*

@Composable
fun HomeBottomSheetContent(
    isResetEnabled: Boolean,
    isShowWinnersEnabled: Boolean,
    isFinalStandingsEnabled: Boolean,
    playoffCompletedState: Boolean,
    onResetPlayoffClick: () -> Unit,
    onShowWinnersClick: () -> Unit,
    onFinalStandingsClick: () -> Unit,
    onTeamsMapClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = SMALL_VERTICAL_PADDING)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .width(HANDLE_WIDTH)
                    .height(HANDLE_HEIGHT),
                shape = RoundedCornerShape(25.dp),
                backgroundColor = mainColor
            ) { }
        }
        if (isResetEnabled) {
            BottomSheetRow(
                icon = painterResource(R.drawable.ic_reset_playoffs),
                title = stringResource(R.string.reset_playoffs_text)
            ) {
                onResetPlayoffClick()
            }
        }

        if (isShowWinnersEnabled) {
            BottomSheetRow(
                icon = painterResource(R.drawable.ic_show_winners),
                title = stringResource(R.string.show_winners_text)
            ) {
                if (playoffCompletedState) {
                    onShowWinnersClick()
                }
            }
        }

        if (isFinalStandingsEnabled) {
            BottomSheetRow(
                icon = painterResource(R.drawable.ic_final_standings),
                title = stringResource(R.string.final_standings_text)
            ) {
                if (playoffCompletedState) {
                    onFinalStandingsClick()
                }
            }
        }
        BottomSheetRow(
            icon = painterResource(R.drawable.ic_countries_map),
            title = stringResource(id = R.string.countries_map_text)
        ) {
            onTeamsMapClick()
        }

    }
}

@Composable
fun BottomSheetRow(icon: Painter, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(horizontal = LARGE_PADDING, vertical = MEDIUM_VERTICAL_PADDING)
    ) {
        Icon(
            painter = icon,
            contentDescription = title,
            tint = mainColor
        )
        Spacer(modifier = Modifier.width(LARGE_PADDING))
        Text(
            modifier = Modifier,
            text = title,
            color = mainColor,
            fontSize = Typography.h6.fontSize
        )
    }
}