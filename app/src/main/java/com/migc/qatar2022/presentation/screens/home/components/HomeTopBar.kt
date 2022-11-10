package com.migc.qatar2022.presentation.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.migc.qatar2022.R
import com.migc.qatar2022.ui.theme.*

@ExperimentalMaterial3Api
@Composable
fun HomeTopBar(
    onSignInClick: () -> Unit,
    onShareClick: () -> Unit,
    onMenuClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
) {
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
                onClick = { onShareClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Share, stringResource(id = R.string.share_text),
                    tint = mainBackgroundColor
                )
            }
            IconButton(
                onClick = {
                    onMenuClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu, stringResource(R.string.menu_text),
                    tint = mainBackgroundColor
                )
            }
        },
        windowInsets = TopAppBarDefaults.windowInsets,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = mainColor,
            navigationIconContentColor = mainBackgroundColor,
            titleContentColor = mainBackgroundColor,
            actionIconContentColor = mainBackgroundColor
        ),
        scrollBehavior = scrollBehavior
    )
}

@ExperimentalMaterial3Api
@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar(onSignInClick = {}, onShareClick = {}, onMenuClick = {})
}