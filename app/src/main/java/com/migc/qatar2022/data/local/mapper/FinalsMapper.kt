package com.migc.qatar2022.data.local.mapper

import com.migc.qatar2022.data.local.entity.FinalsEntity
import com.migc.qatar2022.domain.model.KnockOutMatch

fun FinalsEntity.toKnockOutMatch(): KnockOutMatch {
    return KnockOutMatch(
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

fun KnockOutMatch.toFinalsEntity(): FinalsEntity {
    return FinalsEntity(
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