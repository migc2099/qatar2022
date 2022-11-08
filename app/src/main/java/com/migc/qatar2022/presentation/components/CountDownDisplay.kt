package com.migc.qatar2022.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.migc.qatar2022.R
import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun CountDownDisplay(
    modifier: Modifier = Modifier
) {
    val days = remember { mutableStateOf(0) }
    val hours = remember { mutableStateOf(0) }
    val minutes = remember { mutableStateOf(0) }
    val seconds = remember { mutableStateOf(0) }

    val worldCupTimeStamp = Constants.WORLD_CUP_START_DATE_MILLIS

    LaunchedEffect(key1 = true) {
        while (true) {
            val currentTimestamp = System.currentTimeMillis()
            val countDownResult = millisToDaysHrMinSec(worldCupTimeStamp - currentTimestamp)
            if (days.value != countDownResult.days) {
                days.value = countDownResult.days
            }
            days.value = countDownResult.days
            if (hours.value != countDownResult.hours) {
                hours.value = countDownResult.hours
            }
            hours.value = countDownResult.hours
            if (minutes.value != countDownResult.minutes) {
                minutes.value = countDownResult.minutes
            }
            seconds.value = countDownResult.seconds
            delay(1000L)
        }
    }

    val daysScale = remember { Animatable(1f) }
    LaunchedEffect(key1 = days.value) {
        daysScale.animateTo(
            targetValue = 1.4f,
            animationSpec = tween(
                durationMillis = 100,
                easing = FastOutSlowInEasing
            )
        )
        daysScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 100,
                easing = FastOutSlowInEasing
            )
        )
    }

    val hoursScale = remember { Animatable(1f) }
    LaunchedEffect(key1 = hours.value) {
        hoursScale.animateTo(
            targetValue = 1.4f,
            animationSpec = tween(
                durationMillis = 100,
                easing = FastOutSlowInEasing
            )
        )
        hoursScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 100,
                easing = FastOutSlowInEasing
            )
        )
    }

    val minsScale = remember { Animatable(1f) }
    LaunchedEffect(key1 = minutes.value) {
        minsScale.animateTo(
            targetValue = 1.4f,
            animationSpec = tween(
                durationMillis = 100,
                easing = FastOutSlowInEasing
            )
        )
        minsScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 100,
                easing = FastOutSlowInEasing
            )
        )
    }

    val secsScale = remember { Animatable(1f) }
    LaunchedEffect(key1 = seconds.value) {
        secsScale.animateTo(
            targetValue = 1.4f,
            animationSpec = tween(
                durationMillis = 100,
                easing = FastOutSlowInEasing
            )
        )
        secsScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 100,
                easing = FastOutSlowInEasing
            )
        )
    }

    Card(
        modifier = modifier
            .padding(vertical = MEDIUM_VERTICAL_PADDING)
            .fillMaxWidth()
            .height(96.dp),
        shape = RoundedCornerShape(SMALL_ROUND_CORNER),
        backgroundColor = mainColor,
        elevation = SMALL_ELEVATION
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (days.value > 0 && hours.value > 0 && minutes.value > 0 && seconds.value > 0){
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        color = mainBackgroundColor,
                        text = stringResource(R.string.world_cup_starts_text),
                        fontSize = Typography.subtitle1.fontSize,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        color = mainBackgroundColor,
                        text = stringResource(R.string.world_cup_started_text),
                        fontSize = Typography.h6.fontSize,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                if (days.value > 0 && hours.value > 0 && minutes.value > 0 && seconds.value > 0) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        DisplayNumber(
                            timeValue = days.value.toString(),
                            timeLabel = stringResource(R.string.days_text),
                            scaleValue = daysScale.value
                        )
                        Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
                        DisplayNumber(
                            timeValue = hours.value.toString(),
                            timeLabel = stringResource(R.string.hours_text),
                            scaleValue = hoursScale.value
                        )
                        Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
                        DisplayNumber(
                            timeValue = minutes.value.toString(),
                            timeLabel = stringResource(R.string.minutes_text),
                            scaleValue = minsScale.value
                        )
                        Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
                        DisplayNumber(
                            timeValue = seconds.value.toString(),
                            timeLabel = stringResource(R.string.seconds_text),
                            scaleValue = secsScale.value
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun DisplayNumber(timeValue: String, timeLabel: String, scaleValue: Float) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.graphicsLayer {
                scaleX = scaleValue
                scaleY = scaleValue
            },
            color = mainBackgroundColor,
            text = timeValue,
            fontSize = Typography.subtitle2.fontSize
        )
        Text(
            color = mainBackgroundColor,
            text = timeLabel,
            fontSize = Typography.subtitle2.fontSize
        )
    }
}

@Composable
@Preview
fun CountDownDisplayPreview() {
    CountDownDisplay()
}

private fun millisToDaysHrMinSec(diffInMillis: Long): CountDownResult {
    if (diffInMillis <= 0) {
        return CountDownResult(0, 0, 0, 0)
    }
    val diffInSeconds = diffInMillis / 1000
    val diffInMinutes = diffInSeconds / 60
    val diffInHours = diffInMinutes / 60

    val days = diffInHours / 24
    val hours = diffInHours - (days * 24)
    val minutes = (diffInMinutes - ((days * 24 * 60) + (hours * 60)))
    val seconds = (diffInSeconds - ((days * 24 * 60 * 60) + (hours * 60 * 60) + (minutes * 60)))

//    Log.d("millis", "days: $days hours: $hours minutes: $minutes seconds: $seconds")
    return CountDownResult(days.toInt(), hours.toInt(), minutes.toInt(), seconds.toInt())
}

private data class CountDownResult(val days: Int, val hours: Int, val minutes: Int, val seconds: Int)