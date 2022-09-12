package com.migc.qatar2022.data.local.mapper

import com.migc.qatar2022.data.local.dao.StandingsDao
import com.migc.qatar2022.data.local.entity.TeamEntity
import com.migc.qatar2022.domain.model.Team

fun StandingsDao.TeamStanding.toTeam(): Team {
    return Team(
        teamId = teamId,
        teamName = teamName,
        flagUri = flagLocation,
        goalsDifference = goalsDifference,
        points = points
    )
}