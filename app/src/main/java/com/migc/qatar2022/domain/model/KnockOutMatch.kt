package com.migc.qatar2022.domain.model

data class KnockOutMatch(
    val roundKey: Int,
    val firstTeam: String,
    val secondTeam: String,
    val firstTeamScore: Int = 0,
    val secondTeamScore: Int = 0,
    val firstTeamPKScore: Int = 0,
    val secondTeamPKScore: Int = 0,
    val loserTeam: String,
    val winnerTeam: String
)
