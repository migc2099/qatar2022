package com.migc.qatar2022.data.remote.dto

import com.migc.qatar2022.domain.model.Predictions

data class PredictionsDto(
    val first_place: Int = 0,
    val second_place: Int = 0,
    val third_place: Int = 0,
    val betting_odds: Int = 0
)

fun PredictionsDto.toPredictions(): Predictions {
    return Predictions(
        firstPlace = first_place,
        secondPlace = second_place,
        thirdPlace = third_place,
        bettingOdds = betting_odds
    )
}


