package com.migc.qatar2022.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.migc.qatar2022.common.Constants.FINALS_TABLE

@Entity(tableName = FINALS_TABLE)
data class FinalsEntity(
    @PrimaryKey
    val roundKey: Int,
    val firstTeamId: String? = null,
    val secondTeamId: String? = null,
    val firstTeamScore: Int = 0,
    val secondTeamScore: Int = 0,
    val firstTeamPKScore: Int = 0,
    val secondTeamPKScore: Int = 0,
    val loserTeam: String? = null,
    val winnerTeam: String? = null
)
