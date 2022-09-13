package com.migc.qatar2022.data.local.mapper

import com.migc.qatar2022.data.local.entity.PlayoffEntity
import com.migc.qatar2022.domain.model.Playoff

fun PlayoffEntity.toPlayoff(): Playoff {
    return Playoff(
        roundKey = roundKey,
        firstTeam = firstTeamId!!,
        secondTeam = secondTeamId!!,
        firstTeamScore = firstTeamScore,
        secondTeamScore = secondTeamScore,
        firstTeamPKScore = firstTeamPKScore,
        secondTeamPKScore = secondTeamPKScore,
        loserTeam = loserTeam!!,
        winnerTeam = winnerTeam!!
    )
}

fun Playoff.toPlayoffEntity(): PlayoffEntity {
    return PlayoffEntity(
        roundKey = roundKey,
        firstTeamId = firstTeam,
        secondTeamId = secondTeam,
        firstTeamScore = firstTeamScore,
        secondTeamScore = secondTeamScore,
        firstTeamPKScore = firstTeamPKScore,
        secondTeamPKScore = secondTeamPKScore,
        loserTeam = loserTeam,
        winnerTeam = winnerTeam
    )
}