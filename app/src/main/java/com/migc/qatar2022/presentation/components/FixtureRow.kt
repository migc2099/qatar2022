package com.migc.qatar2022.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.migc.qatar2022.R
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.ui.theme.*

@Composable
fun FixtureRow(
    fixture: Fixture,
    homeTeamScoreValue: () -> String,
    awayTeamScoreValue: () -> String,
    homeTeamScore: (String) -> Unit,
    awayTeamScore: (String) -> Unit
) {
    val leftText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val rightText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    leftText.value = TextFieldValue(homeTeamScoreValue())
    rightText.value = TextFieldValue(awayTeamScoreValue())
    Log.d("FixtureRow", "leftText: ${leftText.value} - rightText: ${rightText.value}")
    Log.d("FixtureRow", "${fixture.homeTeam} ${fixture.homeTeamScore}vs${fixture.awayTeamScore} ${fixture.awayTeam}")
    val homeTeamFlag = TeamsData.flagsMap[fixture.homeTeam] ?: R.drawable.ic_launcher_foreground
    val awayTeamFlag = TeamsData.flagsMap[fixture.awayTeam] ?: R.drawable.ic_launcher_foreground

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(mainBackgroundColor)
            .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_VERTICAL_PADDING),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = homeTeamFlag),
            contentDescription = stringResource(R.string.home_team_flag),
            modifier = Modifier.size(FLAG_ROW_IMAGE_SIZE)
        )
        Spacer(modifier = Modifier.width(SMALL_HORIZONTAL_PADDING))
        Text(
            text = fixture.homeTeam,
            color = Color.Black,
            fontSize = Typography.subtitle1.fontSize
        )
        Spacer(modifier = Modifier.width(EXTRA_SMALL_HORIZONTAL_PADDING))
        ScoreTextField(
            textValue = leftText.value,
            onScoreChange = { homeTeamScore(it) }
        )
        Text(
            modifier = Modifier
                .width(18.dp),
            text = " vs ",
            color = Color.Black,
            fontSize = Typography.subtitle2.fontSize,
            textAlign = TextAlign.Center
        )
        ScoreTextField(
            textValue = rightText.value,
            onScoreChange = { awayTeamScore(it) }
        )
        Spacer(modifier = Modifier.width(EXTRA_SMALL_HORIZONTAL_PADDING))
        Text(
            text = fixture.awayTeam,
            color = Color.Black,
            fontSize = Typography.subtitle1.fontSize
        )
        Spacer(modifier = Modifier.width(SMALL_HORIZONTAL_PADDING))
        Image(
            painter = painterResource(id = awayTeamFlag),
            contentDescription = stringResource(R.string.away_team_flag),
            modifier = Modifier.size(FLAG_ROW_IMAGE_SIZE)
        )
    }
}

@Composable
fun ScoreTextField(textValue: TextFieldValue, onScoreChange: (String) -> Unit) {
    val text = remember {
        mutableStateOf("")
    }
    text.value = textValue.text
    Log.d("ScoreTextField", text.value)
    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
            val score = it.trim()
            if (score.isNotEmpty() && score.isDigitsOnly()) {
                onScoreChange(score)
            } else {
                onScoreChange("")
            }
        },
        modifier = Modifier.width(SCORE_TEXT_FIELD_WIDTH),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        shape = CircleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black
        )
    )
}

@Composable
@Preview
fun FixtureRowPreview() {
    FixtureRow(
        fixture = Fixture(1, 1, "A", 0, "My Stadium", "QAT", "ECU"),
        homeTeamScoreValue = { "1" },
        awayTeamScoreValue = { "0" },
        homeTeamScore = {},
        awayTeamScore = {}
    )
}