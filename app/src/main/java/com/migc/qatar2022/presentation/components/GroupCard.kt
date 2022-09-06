package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.migc.qatar2022.R
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.ui.theme.*

@Composable
fun GroupCard(modifier: Modifier, size: Dp, group: String, teams: List<Team>) {
    Card(
        modifier = modifier.size(size),
        shape = GroupCardShape(),
        backgroundColor = mainColor
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.8f)
                .padding(horizontal = EXTRA_LARGE_PADDING, vertical = LARGE_PADDING),
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text(
                    modifier = Modifier
                        .padding(bottom = SMALL_VERTICAL_PADDING)
                        .fillMaxWidth(),
                    color = mainBackgroundColor,
                    text = group,
                    fontSize = Typography.subtitle1.fontSize,
                    textAlign = TextAlign.Center
                )
            }
            teams.forEach { team ->
                item() {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(EXTRA_SMALL_VERTICAL_PADDING),
                        shape = RoundedCornerShape(MEDIUM_ROUND_CORNER),
                        backgroundColor = mainBackgroundColor
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = team.flagUri),
                                modifier = Modifier.size(FLAG_ROW_IMAGE_SIZE),
                                contentDescription = stringResource(R.string.team_flag)
                            )
                            Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
                            Text(
                                color = Color.Black,
                                text = team.teamName,
                                fontSize = Typography.subtitle1.fontSize
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(SMALL_VERTICAL_GAP))
                }
            }
        }
    }

}

@Composable
@Preview
fun GroupCardPreview() {
    GroupCard(
        modifier = Modifier,
        size = 296.dp,
        group = "Group A",
        teams = listOf(
            Team("QAT", "Qatar", R.drawable.flag_qatar),
            Team("ECU", "Ecuador", R.drawable.flag_ecuador),
            Team("SEN", "Senegal", R.drawable.flag_senegal),
            Team("NET", "Netherlands", R.drawable.flag_netherlands)
        )
    )
}