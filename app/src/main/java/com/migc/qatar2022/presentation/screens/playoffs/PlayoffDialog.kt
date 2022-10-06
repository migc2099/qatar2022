package com.migc.qatar2022.presentation.screens.playoffs

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.text.isDigitsOnly
import com.migc.qatar2022.R
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.ui.theme.*

@Composable
fun PlayoffDialog(
    playoff: Playoff,
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: (Playoff) -> Unit
) {
    val editedPlayOff = playoff.copy(
        loserTeam = "",
        winnerTeam = ""
    )

    val isScoreTied = remember { mutableStateOf(false) }

    when (playoff.firstTeamScore?.compareTo(0)) {
        in 0..Int.MAX_VALUE -> {
            when (playoff.secondTeamScore?.compareTo(0)) {
                in 0..Int.MAX_VALUE -> {
                    when (playoff.firstTeamScore?.compareTo(playoff.secondTeamScore!!)) {
                        0 -> isScoreTied.value = true
                    }
                }
            }
        }
    }

    Dialog(
        onDismissRequest = { onDismiss() }) {
        Card(
            shape = RoundedCornerShape(MEDIUM_ROUND_CORNER),
            backgroundColor = mainBackgroundColor,
            elevation = SMALL_ELEVATION
        ) {
            Column(
                modifier = Modifier
                    .width(DIALOG_WIDTH)
                    .padding(MEDIUM_PADDING),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = LARGE_PADDING),
                    text = stringResource(R.string.enter_match_result_text),
                    textAlign = TextAlign.Center,
                    fontSize = Typography.subtitle1.fontSize
                )
                Row(
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth()
                        .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    FlagAndName(teamId = playoff.firstTeam)
                    Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Row {
                            ScoreTextField(
                                initialScore = editedPlayOff.firstTeamScore,
                                onScoreChange = {
                                    val score = it.trim()
                                    if (score.isNotEmpty() && score.isDigitsOnly()) {
                                        editedPlayOff.firstTeamScore = score.toInt()
                                        isScoreTied.value = editedPlayOff.firstTeamScore == editedPlayOff.secondTeamScore
                                    } else {
                                        editedPlayOff.firstTeamScore = -1
                                        isScoreTied.value = false
                                    }
                                },
                                isPkField = false
                            )
                            Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
                            if (isScoreTied.value) {
                                ScoreTextField(
                                    initialScore = editedPlayOff.firstTeamPKScore,
                                    onScoreChange = {
                                        val score = it.trim()
                                        if (score.isNotEmpty() && score.isDigitsOnly()) {
                                            editedPlayOff.firstTeamPKScore = score.toInt()
                                        } else {
                                            editedPlayOff.firstTeamPKScore = -1
                                        }
                                    },
                                    isPkField = true
                                )
                            } else {
                                Spacer(modifier = Modifier.width(50.dp))
                            }
                        }

                    }
                }
                Row(
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth()
                        .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    FlagAndName(teamId = playoff.secondTeam)
                    Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Row {
                            ScoreTextField(
                                initialScore = editedPlayOff.secondTeamScore,
                                onScoreChange = {
                                    val score = it.trim()
                                    if (score.isNotEmpty() && score.isDigitsOnly()) {
                                        editedPlayOff.secondTeamScore = score.toInt()
                                        isScoreTied.value = editedPlayOff.firstTeamScore == editedPlayOff.secondTeamScore
                                    } else {
                                        editedPlayOff.secondTeamScore = -1
                                        isScoreTied.value = false
                                    }
                                },
                                isPkField = false
                            )
                            Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
                            if (isScoreTied.value) {
                                ScoreTextField(
                                    initialScore = editedPlayOff.secondTeamPKScore,
                                    onScoreChange = {
                                        val score = it.trim()
                                        if (score.isNotEmpty() && score.isDigitsOnly()) {
                                            editedPlayOff.secondTeamPKScore = score.toInt()
                                        } else {
                                            editedPlayOff.secondTeamPKScore = -1
                                        }
                                    },
                                    isPkField = true
                                )
                            } else {
                                Spacer(modifier = Modifier.width(50.dp))
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.width(MEDIUM_VERTICAL_GAP))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MEDIUM_PADDING)
                ) {

                    TextButton(onClick = onNegativeClick) {
                        Text(text = stringResource(R.string.cancel_text))
                    }
                    Spacer(modifier = Modifier.width(LARGE_PADDING))
                    TextButton(
                        onClick = {

                            Log.d("LOG", " first team score: ${editedPlayOff.firstTeamScore}")
                            Log.d("LOG", " second team score: ${editedPlayOff.secondTeamScore}")
                            Log.d("LOG", " second team PK score: ${editedPlayOff.firstTeamPKScore}")
                            Log.d("LOG", " second team PK score: ${editedPlayOff.secondTeamPKScore}")

                            if (editedPlayOff.firstTeamScore != null && editedPlayOff.secondTeamScore != null) {

                                if (editedPlayOff.firstTeamScore!! > -1 && editedPlayOff.secondTeamScore!! > -1) {
                                    if (editedPlayOff.firstTeamScore == editedPlayOff.secondTeamScore) {
                                        // penalty kicks
                                        if (editedPlayOff.firstTeamPKScore != null && editedPlayOff.secondTeamPKScore != null) {
                                            if (editedPlayOff.firstTeamPKScore!! >= -1 && editedPlayOff.secondTeamPKScore!! >= -1) {
                                                if (editedPlayOff.firstTeamPKScore != editedPlayOff.secondTeamPKScore) {
                                                    onPositiveClick(editedPlayOff)
                                                }
                                            }
                                        }
                                    } else {
                                        onPositiveClick(editedPlayOff)
                                    }
                                }
                            }

                        }) {
                        Text(text = stringResource(R.string.okay_text))
                    }
                }
            }
        }
    }

}

@Composable
fun FlagAndName(teamId: String) {
    Image(
        painter = painterResource(id = TeamsData.flagsMap[teamId]!!),
        modifier = Modifier.size(FLAG_ROW_IMAGE_SIZE),
        contentDescription = stringResource(R.string.team_flag)
    )
    Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
    Text(
        modifier = Modifier.width(100.dp),
        color = Color.Black,
        text = TeamsData.countriesMap[teamId]!!,
        fontSize = Typography.subtitle2.fontSize
    )
}

@Composable
fun ScoreTextField(initialScore: Int?, onScoreChange: (String) -> Unit, isPkField: Boolean) {
    var score = ""
    if (initialScore != null) {
        score = initialScore.toString()
    }
    val scoreInput = remember { mutableStateOf(TextFieldValue(score)) }
    TextField(
        value = scoreInput.value,
        onValueChange = {
            if (it.text.length <= 2) {
                scoreInput.value = it
                onScoreChange(it.text)
            }
        },
        modifier = Modifier.width(SCORE_TEXT_FIELD_WIDTH),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        label = { if (isPkField) Text(stringResource(R.string.penalty_kick_text), color = Color.DarkGray.copy(alpha = 0.5f)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        singleLine = true,
        shape = CircleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = mainColor,
            backgroundColor = Color.LightGray,
            cursorColor = mainColor
        )
    )
}

@Composable
@Preview
fun PlayoffDialogPreview() {
    PlayoffDialog(
        playoff = Playoff(51, firstTeam = "QAT", secondTeam = "ARG"),
        onDismiss = {},
        onNegativeClick = {},
        onPositiveClick = {}
    )
}
