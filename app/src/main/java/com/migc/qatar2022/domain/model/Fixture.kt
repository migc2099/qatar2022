package com.migc.qatar2022.domain.model

data class Fixture(
    val matchNumber: Int,
    val roundNumber: Int,
    val group: String,
    val date: String,
    val location: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamScore: Int = 0,
    val awayTeamScore: Int = 0
)
