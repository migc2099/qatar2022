package com.migc.qatar2022.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.migc.qatar2022.R
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.ui.theme.*

@Composable
fun FixtureRow(
    homeTeam: Team,
    awayTeam: Team,
    homeTeamScore: (Int) -> Unit,
    awayTeamScore: (Int) -> Unit
) {
    val leftText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val rightText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(mainBackgroundColor)
            .height(64.dp)
            .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_VERTICAL_PADDING),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = homeTeam.flagUri),
            contentDescription = "home team flag",
            modifier = Modifier.size(FLAG_ROW_IMAGE_SIZE)
        )
        Spacer(modifier = Modifier.width(SMALL_HORIZONTAL_PADDING))
        Text(
            text = homeTeam.teamId,
            color = Color.Black,
            fontSize = Typography.subtitle1.fontSize
        )
        Spacer(modifier = Modifier.width(EXTRA_SMALL_HORIZONTAL_PADDING))
        TextField(
            value = leftText.value,
            onValueChange = {
                leftText.value = it
            },
            modifier = Modifier
                .width(50.dp),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black
            )
        )
        Text(
            modifier = Modifier
                .width(18.dp),
            text = " vs ",
            color = Color.Black,
            fontSize = Typography.subtitle2.fontSize,
            textAlign = TextAlign.Center
        )
        TextField(
            value = rightText.value,
            onValueChange = {
                rightText.value = it
            },
            modifier = Modifier
                .width(50.dp),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.width(EXTRA_SMALL_HORIZONTAL_PADDING))
        Text(
            text = awayTeam.teamId,
            color = Color.Black,
            fontSize = Typography.subtitle1.fontSize
        )
        Spacer(modifier = Modifier.width(SMALL_HORIZONTAL_PADDING))
        Image(
            painter = painterResource(id = awayTeam.flagUri),
            contentDescription = "home team flag",
            modifier = Modifier.size(FLAG_ROW_IMAGE_SIZE)
        )
    }
}

@Composable
@Preview
fun FixtureRowPreview() {
    FixtureRow(
        homeTeam = Team("QAT", "Qatar", R.drawable.flag_qatar),
        awayTeam = Team("ECU", "Ecuador", R.drawable.flag_ecuador),
        homeTeamScore = {},
        awayTeamScore = {}
    )
}