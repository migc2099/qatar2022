package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
        BottomSheetHandle()
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