package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.migc.qatar2022.ui.theme.*

@Composable
fun Coin(title: String, value: Int) {
    Card(
        modifier = Modifier.size(COIN_SIZE),
        shape = CircleShape,
        backgroundColor = mainBackgroundColor,
        border = BorderStroke(COIN_BORDER_STROKE, mainColor)
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = mainColor,
                fontSize = Typography.overline.fontSize
            )
            Text(
                text = value.toString(),
                color = mainColor,
                fontSize = Typography.h5.fontSize,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
        }
    }
}

@Composable
@Preview
fun CoinPreview() {
    Coin(title = "Appearances", value = 4)
}