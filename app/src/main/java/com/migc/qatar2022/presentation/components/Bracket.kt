package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.migc.qatar2022.ui.theme.SMALL_PADDING
import com.migc.qatar2022.ui.theme.mainBackgroundColor

@Composable
fun Bracket(modifier: Modifier, rotate: Boolean) {
    var degrees = 0f
    if (rotate) degrees = 180f
    Canvas(
        modifier = modifier.padding(horizontal = SMALL_PADDING)
    ) {
        rotate(degrees) {
            drawLine(
                color = mainBackgroundColor,
                start = Offset.Zero,
                end = Offset(size.width / 2, 0f),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
            drawLine(
                color = mainBackgroundColor,
                start = Offset(0f, size.height),
                end = Offset(size.width / 2, size.height),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
            drawLine(
                color = mainBackgroundColor,
                start = Offset(size.width / 2, 0f),
                end = Offset(size.width / 2, size.height),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
            drawLine(
                color = mainBackgroundColor,
                start = Offset(size.width / 2, size.height / 2),
                end = Offset(size.width, size.height / 2),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
@Preview
fun BracketPreview() {
    Bracket(
        modifier = Modifier
            .padding(8.dp)
            .size(72.dp, 104.dp),
        rotate = true
    )
}