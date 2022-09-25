package com.migc.qatar2022.presentation.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.migc.qatar2022.R
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.domain.model.Group
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.ui.theme.*

@Composable
fun GroupCard(
    modifier: Modifier,
    group: Group,
    teamsStats: List<Team>,
    onClick: (String) -> Unit
) {
    val rowScale = remember { Animatable(1.1f) }
    LaunchedEffect(key1 = true) {
        rowScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
    }
    Card(
        modifier = modifier
            .size(GROUP_CARD_SIZE)
            .clickable {
                onClick(group.groupId)
            }
            .graphicsLayer {
                scaleX = rowScale.value
                scaleY = rowScale.value
            },
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
                    text = group.name,
                    fontSize = Typography.subtitle1.fontSize,
                    textAlign = TextAlign.Center
                )
            }
            teamsStats.forEach { teamStats ->
                Log.d("GroupCard", teamsStats.toString())
                item() {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(EXTRA_SMALL_VERTICAL_PADDING)
                            .graphicsLayer {
                                scaleX = rowScale.value
                                scaleY = rowScale.value
                            },
                        shape = RoundedCornerShape(MEDIUM_ROUND_CORNER),
                        backgroundColor = mainBackgroundColor
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(id = TeamsData.flagsMap[teamStats.teamId]!!),
                                modifier = Modifier.size(FLAG_ROW_IMAGE_SIZE),
                                contentDescription = stringResource(R.string.team_flag)
                            )
                            Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
                            Text(
                                color = Color.Black,
                                text = teamStats.teamName,
                                fontSize = Typography.subtitle1.fontSize
                            )
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        color = Color.Black,
                                        text = teamStats.points.toString(),
                                        fontSize = Typography.subtitle1.fontSize,
                                        textAlign = TextAlign.End
                                    )
                                    Spacer(modifier = Modifier.width(SMALL_PADDING))
                                    Text(
                                        color = Color.Black,
                                        text = "(${teamStats.goalsDifference})",
                                        fontSize = Typography.caption.fontSize,
                                        textAlign = TextAlign.End
                                    )
                                }

                            }
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
        group = Group("A", "Group A"),
        teamsStats = listOf(
            Team("QAT", "Qatar", R.drawable.flag_qatar, 0, 0),
            Team("ECU", "Ecuador", R.drawable.flag_ecuador, 0, 0),
            Team("SEN", "Senegal", R.drawable.flag_senegal, 0, 0),
            Team("NET", "Netherlands", R.drawable.flag_netherlands, 0, 0)
        ),
        onClick = {}
    )
}