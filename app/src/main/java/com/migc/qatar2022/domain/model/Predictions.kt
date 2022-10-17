package com.migc.qatar2022.domain.model

data class Predictions(
    val teamId: String = "",
    val firstPlace: Int = 0,
    val secondPlace: Int = 0,
    val thirdPlace: Int = 0,
    val bettingOdds: String = ""
)
