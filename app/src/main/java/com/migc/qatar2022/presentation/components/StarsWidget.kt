package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.migc.qatar2022.R
import com.migc.qatar2022.ui.theme.*

@Composable
fun StarsWidget(
    modifier: Modifier,
    years: List<Int>,
    color: Color,
    scaleFactor: Float = 4f
) {
    val startPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = startPathString).toPath()
    }
    val startPathBounds = remember {
        starPath.getBounds()
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(STARS_SPACE_BETWEEN)
    ) {
        repeat(years.size) { index ->
            Star(
                starPath = starPath,
                starPathBounds = startPathBounds,
                scaleFactor = scaleFactor,
                value = years[index].toString(),
                color = color
            )
        }

    }
}

@Composable
fun Star(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float,
    value: String,
    color: Color
) {
    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(42.dp)) {
            scale(scale = scaleFactor) {
                val canvasSize = size
                val pathWidth = starPathBounds.width
                val pathHeight = starPathBounds.height
                val left = (canvasSize.width / 2f) - (pathWidth / 1.75f)
                val top = (canvasSize.height / 2f) - (pathHeight / 1.75f)

                translate(
                    left = left,
                    top = top
                ) {
                    drawPath(
                        path = starPath,
                        color = color
                    )
                }
            }
        }
        Text(text = value, color = mainColor, fontSize = Typography.overline.fontSize)
    }

}

@Composable
@Preview
fun StartsWidgetPreview() {
    StarsWidget(
        modifier = Modifier.fillMaxWidth(),
        years = listOf(1958, 1962, 1970),
        color = goldColor,
        scaleFactor = 4f
    )
}