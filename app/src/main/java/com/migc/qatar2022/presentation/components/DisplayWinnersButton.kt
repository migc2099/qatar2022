package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.migc.qatar2022.R
import com.migc.qatar2022.ui.theme.LARGE_BUTTON_HEIGHT
import com.migc.qatar2022.ui.theme.MEDIUM_PADDING
import com.migc.qatar2022.ui.theme.MEDIUM_ROUND_CORNER
import com.migc.qatar2022.ui.theme.mainColor

@Composable
fun DisplayWinnersButton(enabled: Boolean, onClick: () -> Unit) {
    TextButton(
        modifier = Modifier
            .padding(MEDIUM_PADDING)
            .height(LARGE_BUTTON_HEIGHT)
            .fillMaxSize(),
        enabled = enabled,
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(MEDIUM_ROUND_CORNER),
        border = BorderStroke(2.dp, mainColor)
    ) {
        Text(
            text = stringResource(R.string.show_winners_text),
            color = mainColor
        )
    }
}