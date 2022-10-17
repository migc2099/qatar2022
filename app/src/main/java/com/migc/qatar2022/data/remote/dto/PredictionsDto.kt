package com.migc.qatar2022.data.remote.dto

import com.migc.qatar2022.domain.model.Predictions

data class PredictionsDto(
    val id: String = "",
    val first_place: Int = 0,
    val second_place: Int = 0,
    val third_place: Int = 0,
    val betting_odds: String = ""
)

fun PredictionsDto.toPredictions(): Predictions {
    return Predictions(
        teamId = id,
        firstPlace = first_place,
        secondPlace = second_place,
        thirdPlace = third_place,
        bettingOdds = betting_odds
    )
}


