package com.migc.qatar2022.data.local.mapper

import com.migc.qatar2022.data.local.entity.FixtureEntity
import com.migc.qatar2022.domain.model.Fixture

fun FixtureEntity.toFixture(): Fixture {
    return Fixture(
        matchNumber = matchNumber,
        roundNumber = roundNumber,
        group = groupKey,
        date = date,
        location = location,
        homeTeam = homeTeam,
        awayTeam = awayTeam,
        homeTeamScore = homeTeamScore,
        awayTeamScore = awayTeamScore
    )
}

fun Fixture.toFixtureEntity(): FixtureEntity {
    return FixtureEntity(
        matchNumber = matchNumber,
        roundNumber = roundNumber,
        groupKey = group,
        date =  date,
        location = location,
        homeTeam = homeTeam,
        awayTeam = awayTeam,
        homeTeamScore = homeTeamScore,
        awayTeamScore = awayTeamScore
    )
}