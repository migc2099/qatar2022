package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.migc.qatar2022.R
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.ui.theme.EXTRA_SMALL_PADDING
import com.migc.qatar2022.ui.theme.FLAG_ROW_IMAGE_SIZE
import com.migc.qatar2022.ui.theme.SMALL_ELEVATION

@Composable
fun TeamFlag(teamId: String, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(EXTRA_SMALL_PADDING)
            .height(FLAG_ROW_IMAGE_SIZE)
            .fillMaxWidth(),
        backgroundColor = Color.Gray,
        elevation = SMALL_ELEVATION
    ) {
        Image(
            painter = painterResource(id = TeamsData.flagsMap[teamId]!!),
            contentDescription = stringResource(R.string.home_team_flag),
            modifier = Modifier.size(FLAG_ROW_IMAGE_SIZE)
        )
    }
}