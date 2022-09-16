package com.migc.qatar2022.domain.model

data class Playoff(
    val roundKey: Int,
    var firstTeam: String = "",
    var secondTeam: String = "",
    var firstTeamScore: Int? = null,
    var secondTeamScore: Int? = null,
    var firstTeamPKScore: Int? = null,
    var secondTeamPKScore: Int? = null,
    var loserTeam: String = "",
    var winnerTeam: String = ""
)
