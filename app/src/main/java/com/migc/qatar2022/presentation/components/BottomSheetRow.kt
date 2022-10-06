package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.migc.qatar2022.ui.theme.LARGE_PADDING
import com.migc.qatar2022.ui.theme.MEDIUM_VERTICAL_PADDING
import com.migc.qatar2022.ui.theme.Typography
import com.migc.qatar2022.ui.theme.mainColor

@Composable
fun BottomSheetRow(icon: Painter, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(horizontal = LARGE_PADDING, vertical = MEDIUM_VERTICAL_PADDING)
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