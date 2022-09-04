package com.migc.qatar2022.data.local.mapper

import com.migc.qatar2022.data.local.entity.StandingsEntity
import com.migc.qatar2022.domain.model.TeamStat

fun StandingsEntity.toTeamsStat(): TeamStat {
    return TeamStat(
        teamId = teamId,
        groupKey = groupKey,
        groupPosition = groupPosition,
        gamesPlayed = gamesPlayed,
        wins = wins,
        draws = draws,
        loses = loses,
        goalsInFavor = goalsInFavor,
        goalsAgainst = goalsAgainst,
        points = points
    )
}

fun TeamStat.toStandingsEntity(): StandingsEntity {
    return StandingsEntity(
        teamId = teamId,
        groupKey = groupKey,
        groupPosition = groupPosition,
        gamesPlayed = gamesPlayed,
        wins = wins,
        draws = draws,
        loses = loses,
        goalsInFavor = goalsInFavor,
        goalsAgainst = goalsAgainst,
        points = points
    )
}