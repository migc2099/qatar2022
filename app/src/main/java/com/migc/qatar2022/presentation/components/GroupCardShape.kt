package com.migc.qatar2022.presentation.components

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class GroupCardShape : Shape {

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {

        val path = Path().apply {
            moveTo(
                x = (size.width - size.width / 16f),
                y = size.height / 24f
            )
            cubicTo(
                x1 = size.width,
                y1 = size.height / 4f,
                x2 = size.width,
                y2 = size.height - (size.height / 4f),
                x3 = (size.width - size.width / 16f),
                y3 = size.height - (size.height / 24f)
            )
            cubicTo(
                x1 = size.width - size.width / 4f,
                y1 = size.height,
                x2 = size.width / 4f,
                y2 = size.height,
                x3 = size.width / 16f,
                y3 = size.height - (size.height / 24f)
            )
            cubicTo(
                x1 = 0f,
                y1 = size.height - (size.height / 4f),
                x2 = 0f,
                y2 = size.height / 4,
                x3 = size.width / 16f,
                y3 = size.height / 24f
            )
            cubicTo(
                x1 = size.width / 4f,
                y1 = 0f,
                x2 = size.width - (size.width / 4f),
                y2 = 0f,
                x3 = size.width - (size.width / 16f),
                y3 = size.height / 24f
            )
            close()
        }
        return Outline.Generic(path)
    }

}