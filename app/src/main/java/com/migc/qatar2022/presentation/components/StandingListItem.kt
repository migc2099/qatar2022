package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.migc.qatar2022.R
import com.migc.qatar2022.ui.theme.*

@Composable
fun StandingListItem(
    digitsModifier: Modifier,
    position: Int,
    flag: Int,
    teamName: String,
    points: String,
    wins: String,
    draws: String,
    loses: String,
    goalsDiff: String
) {

    var evenRowBackground = Color.Transparent
    if (position.mod(2) == 0) {
        evenRowBackground = rowColor
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(evenRowBackground),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(0.5f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(modifier = digitsModifier, color = mainColor, text = position.toString(), textAlign = TextAlign.End, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
            Image(
                painter = painterResource(id = flag),
                contentDescription = stringResource(R.string.team_flag),
                modifier = Modifier
                    .size(FLAG_ROW_IMAGE_SIZE)
            )
            Spacer(modifier = Modifier.width(MEDIUM_HORIZONTAL_GAP))
            Text(
                modifier = Modifier
                    .width(164.dp)
                    .padding(horizontal = SMALL_HORIZONTAL_PADDING),
                color = mainColor,
                text = teamName,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = Typography.subtitle1.fontSize
            )
        }

        Row(
            modifier = Modifier.weight(0.5f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(36.dp)
                    .height(FLAG_ROW_IMAGE_SIZE + 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(modifier = digitsModifier, color = mainColor, text = points, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
            }
            Text(modifier = digitsModifier, color = mainColor, text = wins, textAlign = TextAlign.Center)
            Text(modifier = digitsModifier, color = mainColor, text = draws, textAlign = TextAlign.Center)
            Text(modifier = digitsModifier, color = mainColor, text = loses, textAlign = TextAlign.Center)
            Text(modifier = digitsModifier, color = mainColor, text = goalsDiff, textAlign = TextAlign.Center)
        }


    }

}

@Composable
@Preview
fun StandingListItemPreview() {
    val digitsModifier = Modifier
        .padding(horizontal = SMALL_HORIZONTAL_PADDING)
        .width(24.dp)
    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row() {
                Spacer(modifier = Modifier.weight(0.5f))
                Row(
                    modifier = Modifier.weight(0.5f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(modifier = Modifier.width(36.dp), color = mainColor, text = "Pts", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                    Text(modifier = digitsModifier, color = mainColor, text = "W", textAlign = TextAlign.Center)
                    Text(modifier = digitsModifier, color = mainColor, text = "D", textAlign = TextAlign.Center)
                    Text(modifier = digitsModifier, color = mainColor, text = "L", textAlign = TextAlign.Center)
                    Text(modifier = digitsModifier, color = mainColor, text = "GF", textAlign = TextAlign.Center)
                }
            }


        }
        StandingListItem(
            digitsModifier = digitsModifier,
            position = 2,
            flag = R.drawable.flag_brazil,
            teamName = "Brazil",
            points = "3",
            wins = "2",
            draws = "1",
            loses = "0",
            goalsDiff = "5"
        )
    }

}