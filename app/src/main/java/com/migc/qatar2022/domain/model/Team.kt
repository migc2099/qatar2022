package com.migc.qatar2022.domain.model

data class Team(
    val teamId: String,
    val teamName: String,
    val flagUri: Int,
    val goalsDifference: Int,
    val points: Int = 0
)