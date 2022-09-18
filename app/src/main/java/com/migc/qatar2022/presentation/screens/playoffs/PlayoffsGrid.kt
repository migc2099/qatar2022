package com.migc.qatar2022.presentation.screens.playoffs

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.presentation.components.Bracket
import com.migc.qatar2022.presentation.components.TeamFlag
import com.migc.qatar2022.ui.theme.*

@Composable
fun PlayoffsGrid(
    modifier: Modifier, playoffs: List<Playoff>,
    onPlayoffClick: (Int) -> Unit
) {

    val matches = mutableMapOf(
        49 to Playoff(49),
        50 to Playoff(50),
        51 to Playoff(51),
        52 to Playoff(52),
        53 to Playoff(53),
        54 to Playoff(54),
        55 to Playoff(55),
        56 to Playoff(56),
        57 to Playoff(57),
        58 to Playoff(58),
        59 to Playoff(59),
        60 to Playoff(60),
        61 to Playoff(61),
        62 to Playoff(62),
        63 to Playoff(63),
        64 to Playoff(64)
    )

    playoffs.forEach {
        Log.d("playoffs", "forEach $it")
        matches[it.roundKey] = it
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(13),
        modifier = modifier
            .fillMaxWidth()
            .height(384.dp),
        verticalArrangement = Arrangement.Center
    ) {
        //********************** Round of 16 ****************************************
        item {
            Column(
                modifier = Modifier
                    .width(FLAG_ROW_IMAGE_SIZE * 1.2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                matches[49].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "1A")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "2B")
                    }
                }
                Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                matches[50].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "1C")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "2D")
                    }
                }

                Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                matches[53].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "1E")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "1F")
                    }
                }
                Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                matches[54].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "1G")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "2H")
                    }
                }
            }
        }
        item {
            //************************** Brackets ****************************************
            Column {
                Spacer(modifier = Modifier.height(FLAG_ROW_IMAGE_SIZE + (EXTRA_SMALL_PADDING * 2)))
                Bracket(
                    modifier = Modifier
                        .height((FLAG_ROW_IMAGE_SIZE * 2) + MEDIUM_VERTICAL_GAP + (EXTRA_SMALL_PADDING * 4))
                        .fillMaxWidth(),
                    rotate = false
                )
                Spacer(modifier = Modifier.height((FLAG_ROW_IMAGE_SIZE * 2) + MEDIUM_VERTICAL_GAP + (EXTRA_SMALL_PADDING * 5)))
                Bracket(
                    modifier = Modifier
                        .height((FLAG_ROW_IMAGE_SIZE * 2) + MEDIUM_VERTICAL_GAP + (EXTRA_SMALL_PADDING * 4))
                        .fillMaxWidth(),
                    rotate = false
                )
            }
        }
        //***************************** Round of 8 *****************************
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(GRID_TOP_GAP_PHASE_5))
                matches[57].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "49")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "50")
                    }
                }
                Spacer(modifier = Modifier.height((MEDIUM_VERTICAL_GAP * 2) + (FLAG_ROW_IMAGE_SIZE * 2) + (EXTRA_SMALL_PADDING * 5)))
                matches[58].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "53")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "54")
                    }
                }
            }
        }
        item {
            //************************** Bracket ****************************************
            Column {
                Spacer(modifier = Modifier.height(GRID_TOP_GAP_PHASE_5 + FLAG_ROW_IMAGE_SIZE + (EXTRA_SMALL_PADDING * 2)))
                Bracket(
                    modifier = Modifier
                        .height(GRID_MID_GAP_PHASE_5 + (FLAG_ROW_IMAGE_SIZE * 2) + (EXTRA_SMALL_PADDING * 4))
                        .fillMaxWidth(),
                    rotate = false
                )
            }
        }
        // ************************ Semifinals *********************************
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height((FLAG_ROW_IMAGE_SIZE * 3) + (MEDIUM_VERTICAL_GAP * 1.5f) + (EXTRA_SMALL_PADDING * 6)))
                matches[61].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "57")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "58")
                    }
                }
            }
        }
        item {
            //************************** Filler ****************************************
        }
        //************************* Final and 3rd Place *******************
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(FLAG_ROW_IMAGE_SIZE + 2.dp + (MEDIUM_VERTICAL_GAP / 2)))
                matches[64].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "WC")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "WC")
                    }
                }
                Spacer(modifier = Modifier.height((MEDIUM_VERTICAL_GAP * 2) + (FLAG_ROW_IMAGE_SIZE * 2) + EXTRA_SMALL_PADDING * 6))
                matches[63].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "3rd")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "3rd")
                    }
                }
            }
        }
        item {
            //************************** Filler ****************************************
        }
        // ************************ Semifinals *********************************
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height((FLAG_ROW_IMAGE_SIZE * 3) + (MEDIUM_VERTICAL_GAP * 1.5f) + (EXTRA_SMALL_PADDING * 6)))
                matches[62].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "59")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "60")
                    }
                }
            }
        }
        item {
            Column {
                Spacer(modifier = Modifier.height(GRID_TOP_GAP_PHASE_5 + FLAG_ROW_IMAGE_SIZE + (EXTRA_SMALL_PADDING * 2)))
                Bracket(
                    modifier = Modifier
                        .height(GRID_MID_GAP_PHASE_5 + (FLAG_ROW_IMAGE_SIZE * 2) + (EXTRA_SMALL_PADDING * 4))
                        .fillMaxWidth(),
                    rotate = true
                )
            }
        }
        //***************************** Round of 8 *****************************
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(GRID_TOP_GAP_PHASE_5))
                matches[59].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "51")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "52")
                    }
                }
                Spacer(modifier = Modifier.height((MEDIUM_VERTICAL_GAP * 2) + (FLAG_ROW_IMAGE_SIZE * 2) + (EXTRA_SMALL_PADDING * 5)))
                matches[60].let { playoff ->
                    if (playoff!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.firstTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "55")
                    }
                    if (playoff.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = playoff.secondTeam, onClick = { onPlayoffClick(playoff.roundKey) })
                    } else {
                        TeamPlaceholder(text = "56")
                    }
                }
            }
        }
        item {
            //************************** Filler ****************************************
            Column {
                Spacer(modifier = Modifier.height(FLAG_ROW_IMAGE_SIZE + EXTRA_SMALL_PADDING * 2))
                Bracket(
                    modifier = Modifier
                        .height((FLAG_ROW_IMAGE_SIZE * 2) + MEDIUM_VERTICAL_GAP + (EXTRA_SMALL_PADDING * 4))
                        .fillMaxWidth(),
                    rotate = true
                )
                Spacer(modifier = Modifier.height((FLAG_ROW_IMAGE_SIZE * 2) + MEDIUM_VERTICAL_GAP + (EXTRA_SMALL_PADDING * 5)))
                Bracket(
                    modifier = Modifier
                        .height((FLAG_ROW_IMAGE_SIZE * 2) + MEDIUM_VERTICAL_GAP + (EXTRA_SMALL_PADDING * 4))
                        .fillMaxWidth(),
                    rotate = true
                )
            }
        }
        //********************** Round of 16 ****************************************
        item {
            Column(
                modifier = Modifier
                    .width(FLAG_ROW_IMAGE_SIZE * 1.2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                matches[51].let {
                    if (it!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = it.firstTeam, onClick = { onPlayoffClick(it.roundKey) })
                    } else {
                        TeamPlaceholder(text = "1B")
                    }
                    if (it.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = it.secondTeam, onClick = { onPlayoffClick(it.roundKey) })
                    } else {
                        TeamPlaceholder(text = "2A")
                    }
                }

                Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                matches[52].let {
                    if (it!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = it.firstTeam, onClick = { onPlayoffClick(it.roundKey) })
                    } else {
                        TeamPlaceholder(text = "1D")
                    }
                    if (it.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = it.secondTeam, onClick = { onPlayoffClick(it.roundKey) })
                    } else {
                        TeamPlaceholder(text = "2C")
                    }
                }
                Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                matches[55].let {
                    if (it!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = it.firstTeam, onClick = { onPlayoffClick(it.roundKey) })
                    } else {
                        TeamPlaceholder(text = "1F")
                    }
                    if (it.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = it.secondTeam, onClick = { onPlayoffClick(it.roundKey) })
                    } else {
                        TeamPlaceholder(text = "2E")
                    }
                }

                Spacer(modifier = Modifier.height(MEDIUM_VERTICAL_GAP))
                matches[56].let {
                    if (it!!.firstTeam.isNotEmpty()) {
                        TeamFlag(teamId = it.firstTeam, onClick = { onPlayoffClick(it.roundKey) })
                    } else {
                        TeamPlaceholder(text = "1H")
                    }
                    if (it.secondTeam.isNotEmpty()) {
                        TeamFlag(teamId = it.secondTeam, onClick = { onPlayoffClick(it.roundKey) })
                    } else {
                        TeamPlaceholder(text = "2G")
                    }
                }
            }

        }
    }

}

@Composable
fun TeamPlaceholder(text: String) {
    Card(
        modifier = Modifier
            .padding(EXTRA_SMALL_PADDING)
            .height(FLAG_ROW_IMAGE_SIZE)
            .fillMaxWidth(),
        backgroundColor = Color.Gray,
        elevation = SMALL_ELEVATION
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                color = mainBackgroundColor
            )
        }
    }
}

@Composable
@Preview
fun FinalsScreenPreview() {
    PlayoffsGrid(
        modifier = Modifier,
        playoffs = listOf(
            Playoff(49, secondTeam = "QAT"),
            Playoff(51, firstTeam = "BRA", secondTeam = "URU"),
            Playoff(57, firstTeam = "SPA"),
            Playoff(54, firstTeam = "GER"),
        ),
        onPlayoffClick = {}
    )
}