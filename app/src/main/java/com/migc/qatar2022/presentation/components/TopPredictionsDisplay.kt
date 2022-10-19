package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.migc.qatar2022.R
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.domain.model.Predictions
import com.migc.qatar2022.ui.theme.*
import kotlin.math.roundToInt

@Composable
fun TopPredictionsDisplay(modifier: Modifier, teams: List<Predictions>) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Card(
        modifier = modifier
            .padding(4.dp)
            .offset {
                IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            },
        shape = RoundedCornerShape(MEDIUM_ROUND_CORNER),
        backgroundColor = mainBackgroundColor,
        elevation = LARGE_ELEVATION
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = stringResource(id = R.string.top_teams_text),
                        color = mainColor,
                        fontSize = Typography.caption.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
                items(teams.size) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = SMALL_HORIZONTAL_PADDING),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = TeamsData.flagsMap[teams[index].teamId]!!),
                            contentDescription = stringResource(R.string.home_team_flag),
                            modifier = Modifier.size(FLAG_ROW_IMAGE_SIZE * 0.8f)
                        )
                        Spacer(modifier = Modifier.width(SMALL_HORIZONTAL_PADDING))
                        Text(
                            color = mainColor,
                            text = stringResource(id = TeamsData.countriesMap[teams[index].teamId]!!),
                            fontSize = Typography.subtitle2.fontSize
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(HANDLE_HEIGHT * 2))
                }
            }
            Box(
                modifier = Modifier.background(color = mainBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                BottomSheetHandle()
            }
        }
    }
}

@Composable
@Preview
fun TopPredictionsDisplayPreview() {
    TopPredictionsDisplay(
        modifier = Modifier.size(
            TOP_TEAMS_DISPLAY_WIDTH,
            TOP_TEAMS_DISPLAY_HEIGHT
        ),
        teams = listOf(
            Predictions("POR", 4, 0, 1),
            Predictions("BRA", 3, 1, 2),
            Predictions("URU", 3, 0, 1),
            Predictions("SEN", 2, 0, 3),
            Predictions("ARG", 1, 1, 1)
        )
    )
}