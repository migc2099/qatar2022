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

fun TeamEntity.toTeam():Team{
    return Team(
        teamId = teamId,
        teamName = name,
        flagUri = flagLocation,
        goalsDifference = 0
    )
}

//
//fun StandingsDao.QualifiedTeam.toPlayOffTeam(): PlayOffTeam {
//    return PlayOffTeam(
//        teamId = teamId,
//        teamName = teamName,
//        flagLocation = flagLocation,
//        groupKey = groupKey,
//        position = position
//    )
//}