package com.migc.qatar2022.domain.model

data class TeamStats(
    val countryId: String,
    val groupKey: String,
    val groupPosition: Int = 1,
    val gamesPlayed: Int = 0,
    val wins: Int = 0,
    val draws: Int = 0,
    val loses: Int = 0,
    val goalsInFavor: Int = 0,
    val goalsAgainst: Int = 0,
    val points: Int = 0
)