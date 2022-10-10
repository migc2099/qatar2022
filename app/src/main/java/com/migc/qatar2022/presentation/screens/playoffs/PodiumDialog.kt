package com.migc.qatar2022.presentation.screens.playoffs

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.migc.qatar2022.R
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.presentation.components.TeamFlag
import com.migc.qatar2022.presentation.screens.home.TournamentActionType
import com.migc.qatar2022.presentation.screens.home.TransactionState
import com.migc.qatar2022.ui.theme.*

@Composable
fun PodiumDialog(
    teams: Array<Team>,
    isUserAuthenticated: Boolean,
    lastTournamentActionType: TournamentActionType,
    transactionState: TransactionState,
    onDismiss: () -> Unit,
    onUploadWinners: (List<Team>) -> Unit,
    onStartOver: () -> Unit,
    onClose: () -> Unit
) {
    Log.d("PodiumDialog", "transactionState: $transactionState")

    val isUploadWinnersProcessing = remember {
        mutableStateOf(false)
    }
    isUploadWinnersProcessing.value = transactionState.inProgress
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Card(
            modifier = Modifier
                .width(312.dp)
                .height(396.dp),
            shape = RoundedCornerShape(MEDIUM_ROUND_CORNER),
            backgroundColor = mainBackgroundColor,
            elevation = SMALL_ELEVATION
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    if (teams.size > 2) {
                        PlaceColumn(team = teams[1], place = 2)
                        Spacer(modifier = Modifier.width(1.dp))
                        PlaceColumn(team = teams[0], place = 1)
                        Spacer(modifier = Modifier.width(1.dp))
                        PlaceColumn(team = teams[2], place = 3)
                    }
                }
                Spacer(modifier = Modifier.height(EXTRA_LARGE_PADDING))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MEDIUM_PADDING),
                        horizontalAlignment = Alignment.End
                    ) {
                        TextButton(
                            onClick = {
                                isUploadWinnersProcessing.value = true
                                val winners = listOf(teams[0], teams[1], teams[2])
                                onUploadWinners(winners)
                            },
                            enabled = isUserAuthenticated
                                    && lastTournamentActionType == TournamentActionType.FinalsFinished
                                    && !isUploadWinnersProcessing.value
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = stringResource(id = R.string.upload_winners_text))
                                    if (!isUserAuthenticated) {
                                        Text(
                                            color = Color.Gray,
                                            text = stringResource(id = R.string.authentication_required_text),
                                            fontSize = Typography.overline.fontSize
                                        )
                                    } else {
                                        if (lastTournamentActionType == TournamentActionType.WinnersUpload){
                                            Text(
                                                color = Color.Gray,
                                                text = stringResource(id = R.string.reset_required_text),
                                                fontSize = Typography.overline.fontSize
                                            )
                                        }
                                    }
                                }
                                if (isUploadWinnersProcessing.value) {
                                    Log.d("PodiumDialog", "transactionState: ${transactionState.inProgress}")
                                    CircularProgressIndicator(modifier = Modifier.size(SMALL_CIRCULAR_PROGRESS_SIZE))
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                        TextButton(
                            onClick = {
                                onStartOver()
                            }
                        ) {
                            Text(text = stringResource(R.string.start_over_text))
                        }
                        Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                        TextButton(
                            onClick = {
                                onClose()
                            }
                        ) {
                            Text(text = stringResource(R.string.close_text))
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun PlaceColumn(team: Team, place: Int) {
    var podiumColor: Color = Color.Black
    var podiumHeight: Dp = 64.dp
    when (place) {
        1 -> {
            podiumColor = goldColor
            podiumHeight = FIRST_PLACE_PODIUM_HEIGHT
        }
        2 -> {
            podiumColor = silverColor
            podiumHeight = SECOND_PLACE_PODIUM_HEIGHT
        }
        3 -> {
            podiumColor = bronzeColor
            podiumHeight = THIRD_PLACE_PODIUM_HEIGHT
        }
    }
    Column(
        modifier = Modifier
            .width(PODIUM_WIDTH)
            .height(podiumHeight),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TeamFlag(teamId = team.teamId, onClick = {})
        Spacer(modifier = Modifier.height(SMALL_VERTICAL_GAP))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(podiumColor)
                .border(2.dp, Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .size(PODIUM_STAMP_SIZE)
                    .fillMaxSize(),
                shape = CircleShape,
                backgroundColor = mainBackgroundColor,
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = place.toString(),
                        textAlign = TextAlign.Center,
                        fontSize = Typography.subtitle2.fontSize
                    )
                }

            }
        }
    }
}

@Composable
@Preview
fun PlaceColumnPreview() {
    PlaceColumn(
        team = Team("ARG", "Argentina", R.drawable.flag_argentina, 0, 0),
        place = 1
    )
}

@Composable
@Preview
fun PodiumDialogPreview() {
    PodiumDialog(
        teams = emptyArray(),
        isUserAuthenticated = true,
        lastTournamentActionType = TournamentActionType.WinnersUpload,
        transactionState = TransactionState(),
        onDismiss = { },
        onUploadWinners = { },
        onStartOver = { },
        onClose = { }
    )
}