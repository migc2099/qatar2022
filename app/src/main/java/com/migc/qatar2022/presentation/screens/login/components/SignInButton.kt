package com.migc.qatar2022.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.migc.qatar2022.R
import com.migc.qatar2022.ui.theme.*

@Composable
fun SignInButton(
    text: String,
    icon: Painter? = null,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .width(LARGE_BUTTON_WIDTH)
            .height(MEDIUM_BUTTON_HEIGHT),
        enabled = isEnabled,
        shape = RoundedCornerShape(EXTRA_LARGE_ROUND_CORNER),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = mainColor
        ),
        onClick = onClick
    ) {
        if (icon != null) {
            Image(
                painter = icon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = mainBackgroundColor)
            )
        }
        Text(
            text = text,
            modifier = Modifier.padding(MEDIUM_PADDING),
            fontSize = Typography.subtitle2.fontSize
        )
    }
}