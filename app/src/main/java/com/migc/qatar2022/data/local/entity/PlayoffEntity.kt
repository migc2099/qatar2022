package com.migc.qatar2022.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.migc.qatar2022.common.Constants.PLAYOFFS_TABLE

@Entity(tableName = PLAYOFFS_TABLE)
data class PlayoffEntity(
    @PrimaryKey val roundKey: Int,
    var firstTeamId: String? = null,
    var secondTeamId: String? = null,
    var firstTeamScore: Int? = null,
    var secondTeamScore: Int? = null,
    var firstTeamPKScore: Int?= null,
    var secondTeamPKScore: Int?= null,
    var loserTeam: String? = null,
    var winnerTeam: String? = null
)
