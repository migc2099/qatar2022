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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onResetPlayoffClick()
                    }
                    .padding(horizontal = LARGE_PADDING, vertical = MEDIUM_VERTICAL_PADDING)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_reset_playoffs),
                    contentDescription = stringResource(R.string.reset_playoffs_text),
                    tint = mainColor
                )
                Spacer(modifier = Modifier.width(LARGE_PADDING))
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.reset_playoffs_text),
                    color = mainColor,
                    fontSize = Typography.subtitle1.fontSize
                )
            }
        }

        if (isShowWinnersEnabled) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (playoffCompletedState) {
                            onShowWinnersClick()
                        }
                    }
                    .padding(horizontal = LARGE_PADDING, vertical = MEDIUM_VERTICAL_PADDING)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_show_winners),
                    contentDescription = stringResource(R.string.show_winners_text),
                    tint = mainColor
                )
                Spacer(modifier = Modifier.width(LARGE_PADDING))
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.show_winners_text),
                    color = mainColor,
                    fontSize = Typography.subtitle1.fontSize
                )
            }
        }

        if (isFinalStandingsEnabled) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (playoffCompletedState) {
                            onFinalStandingsClick()
                        }
                    }
                    .padding(horizontal = LARGE_PADDING, vertical = MEDIUM_VERTICAL_PADDING)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_final_standings),
                    contentDescription = stringResource(R.string.final_standings_text),
                    tint = mainColor
                )
                Spacer(modifier = Modifier.width(LARGE_PADDING))
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.final_standings_text),
                    color = mainColor,
                    fontSize = Typography.subtitle1.fontSize
                )
            }
        }

    }
}