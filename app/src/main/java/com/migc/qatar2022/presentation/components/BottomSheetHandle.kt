package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.migc.qatar2022.ui.theme.*

@Composable
fun BottomSheetHandle(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MEDIUM_PADDING),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(HANDLE_WIDTH)
                .height(HANDLE_HEIGHT),
            shape = RoundedCornerShape(HANDLE_ROUNDED_CORNER),
            backgroundColor = mainColor
        ) { }
    }
}