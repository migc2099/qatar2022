package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.migc.qatar2022.ui.theme.*

@Composable
fun BottomSheetRow(icon: Painter, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(BOTTOM_SHEET_ROW_HEIGHT)
            .clickable {
                onClick()
            }
            .padding(horizontal = LARGE_PADDING, vertical = MEDIUM_VERTICAL_PADDING),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = title,
            tint = mainColor
        )
        Spacer(modifier = Modifier.width(LARGE_PADDING))
        Text(
            modifier = Modifier,
            text = title,
            color = mainColor,
            fontSize = Typography.h6.fontSize
        )
    }
}