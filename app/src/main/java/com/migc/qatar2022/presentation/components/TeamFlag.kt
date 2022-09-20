package com.migc.qatar2022.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.migc.qatar2022.R
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.ui.theme.EXTRA_SMALL_PADDING
import com.migc.qatar2022.ui.theme.FLAG_ROW_IMAGE_SIZE
import com.migc.qatar2022.ui.theme.SMALL_ELEVATION
import kotlinx.coroutines.delay

@Composable
fun TeamFlag(teamId: String, onClick: () -> Unit) {
    val flagScale = remember { Animatable(1f) }
    LaunchedEffect(key1 = true) {
        delay(500)
        flagScale.animateTo(
            targetValue = 1.2f,
            animationSpec = tween(
                durationMillis = 200,
                easing = FastOutSlowInEasing
            )
        )
        flagScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 200,
                easing = FastOutSlowInEasing
            )
        )
    }
    Card(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(EXTRA_SMALL_PADDING)
            .height(FLAG_ROW_IMAGE_SIZE)
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = flagScale.value
                scaleY = flagScale.value
            },
        backgroundColor = Color.Gray,
        elevation = SMALL_ELEVATION
    ) {
        Image(
            painter = painterResource(id = TeamsData.flagsMap[teamId]!!),
            contentDescription = stringResource(R.string.home_team_flag),
            modifier = Modifier.size(FLAG_ROW_IMAGE_SIZE)
        )
    }
}