package com.migc.qatar2022.data.local.mapper

import com.migc.qatar2022.data.local.entity.FixtureEntity
import com.migc.qatar2022.domain.model.Fixture

fun FixtureEntity.toFixture(): Fixture {
    return Fixture(
        matchNumber = matchNumber,
        roundNumber = roundNumber,
        group = groupKey,
        date = date.toString(),
        location = location,
        homeTeam = homeTeam,
        awayTeam = awayTeam,
        homeTeamScore = 0,
        awayTeamScore = 0
    )
}

fun Fixture.toFixtureEntity(): FixtureEntity {
    return FixtureEntity(
        matchNumber = matchNumber,
        roundNumber = roundNumber,
        groupKey = group,
        date = date.toInt(),
        location = location,
        homeTeam = homeTeam,
        awayTeam = awayTeam,
        homeTeamScore = 0,
        awayTeamScore = 0
    )
}