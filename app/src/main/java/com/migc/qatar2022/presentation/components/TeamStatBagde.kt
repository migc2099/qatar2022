package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.ui.theme.*

@Composable
fun TeamStatBadge(
    countryInfo: CountryInfo,
    isValueLoading: Boolean,
    text: String,
    textColor: Color = Color.Black,
    backgroundColor: Color = mainColor,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .width(COIN_SIZE)
            .height(PREDICTIONS_BUTTON_HEIGHT)
            .clickable {
                onClick(countryInfo.teamId)
            },
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(EXTRA_LARGE_ROUND_CORNER)
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (isValueLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(SMALL_CIRCULAR_PROGRESS_SIZE),
                    color = mainBackgroundColor
                )
            } else {
                Text(
                    text = text,
                    color = textColor,
                    fontSize = Typography.caption.fontSize,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}