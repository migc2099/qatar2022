package com.migc.qatar2022.data.remote.dto

import com.migc.qatar2022.domain.model.Fixture

data class FixtureDto(
    val MatchNumber: Int,
    val RoundNumber: Int,
    val DateUtc: String,
    val Location: String,
    val HomeTeam: String,
    val AwayTeam: String,
    val Group: String,
    val HomeTeamScore: Int?,
    val AwayTeamScore: Int?
)

fun FixtureDto.toFixture(): Fixture {
    return Fixture(
        matchNumber = MatchNumber,
        roundNumber = RoundNumber,
        group = Group,
        date = DateUtc,
        location = Location,
        homeTeam = HomeTeam,
        awayTeam = AwayTeam,
        homeTeamScore = HomeTeamScore ?: 0,
        awayTeamScore = AwayTeamScore ?: 0
    )
}