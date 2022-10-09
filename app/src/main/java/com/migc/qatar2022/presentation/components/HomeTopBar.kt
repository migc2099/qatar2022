package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.migc.qatar2022.R
import com.migc.qatar2022.ui.theme.*

@Composable
fun HomeTopBar(onSignInClick: () -> Unit, onMenuClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_top_bar_title),
                color = Color.White
            )
        },
        modifier = Modifier.fillMaxWidth(),
        actions = {
            IconButton(
                onClick = {
                    onSignInClick()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_authenticate),
                    contentDescription = stringResource(id = R.string.authenticate_text),
                    tint = mainBackgroundColor
                )
            }
            IconButton(
                onClick = {
                    onMenuClick()
                }
            ) {
                Icon(Icons.Filled.Menu, stringResource(R.string.menu_text))
            }
        },
        backgroundColor = mainColor,
        contentColor = mainBackgroundColor,
        elevation = LARGE_ELEVATION
    )

}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar(onSignInClick = {}, onMenuClick = {})
}