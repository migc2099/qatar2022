package com.migc.qatar2022.domain.model

data class TeamStat(
    val teamId: String,
    val groupKey: String,
    var groupPosition: Int = 1,
    var gamesPlayed: Int = 0,
    var wins: Int = 0,
    var draws: Int = 0,
    var loses: Int = 0,
    var goalsInFavor: Int = 0,
    var goalsAgainst: Int = 0,
    var points: Int = 0
)
