package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.migc.qatar2022.R
import com.migc.qatar2022.ui.theme.*

@Composable
fun GroupDetailsTopBar(group: String, onClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = group,
                color = Color.White
            )
        },
        modifier = Modifier.fillMaxWidth(),
        actions = {
            IconButton(
                onClick = {
                    onClick()
                }
            ) {
                Icon(Icons.Filled.Add, stringResource(R.string.menu_text))
            }
        },
        backgroundColor = mainColor,
        contentColor = mainBackgroundColor,
        elevation = LARGE_ELEVATION
    )

}

@Composable
@Preview
fun GroupDetailsTopBarPreview() {
    GroupDetailsTopBar("Group A", onClick = {})
}