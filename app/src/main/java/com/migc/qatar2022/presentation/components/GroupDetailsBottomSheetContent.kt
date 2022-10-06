package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.migc.qatar2022.R
import com.migc.qatar2022.ui.theme.*

@Composable
fun GroupDetailsBottomSheetContent(
    onGenerateScoresClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = SMALL_VERTICAL_PADDING)
    ) {
        BottomSheetHandle()
        BottomSheetRow(
            icon = painterResource(id = R.drawable.ic_shuffle_24),
            title = stringResource(id = R.string.generate_scores)
        ) {
            onGenerateScoresClick()
        }
        BottomSheetRow(
            icon = painterResource(id = R.drawable.ic_save_24),
            title = stringResource(id = R.string.save_changes_text)
        ) {
            onSaveClick()
        }
    }
}