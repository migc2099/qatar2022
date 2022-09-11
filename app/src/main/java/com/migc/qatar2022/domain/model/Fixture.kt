package com.migc.qatar2022.domain.model

data class Fixture(
    val matchNumber: Int,
    val roundNumber: Int,
    val group: String = "",
    val date: Long,
    val location: String = "",
    val homeTeam: String = "",
    val awayTeam: String = "",
    var homeTeamScore: Int? = null,
    var awayTeamScore: Int? = null
)
