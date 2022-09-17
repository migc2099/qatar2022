package com.migc.qatar2022.presentation.screens.playoffs

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
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
import com.migc.qatar2022.ui.theme.*

@Composable
fun PodiumDialog(
    teams: Array<Team>,
    onDismiss: () -> Unit,
    onStartOver: () -> Unit,
    onClose: () -> Unit
) {
    Log.d("PodiumDialog", teams.toString())
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Card(
            modifier = Modifier
                .width(256.dp)
                .height(244.dp),
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
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MEDIUM_PADDING)
                ) {
                    TextButton(
                        onClick = {
                            onStartOver()
                        }
                    ) {
                        Text(text = stringResource(R.string.start_over_text))
                    }
                    Spacer(modifier = Modifier.width(LARGE_PADDING))
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
        onDismiss = { },
        onStartOver = { },
        onClose = { }
    )
}