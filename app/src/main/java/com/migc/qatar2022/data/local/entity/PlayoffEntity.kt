package com.migc.qatar2022.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.migc.qatar2022.common.Constants.PLAYOFFS_TABLE

@Entity(tableName = PLAYOFFS_TABLE)
data class PlayoffEntity(
    @PrimaryKey val roundKey: Int,
    var firstTeamId: String? = null,
    var secondTeamId: String? = null,
    var firstTeamScore: Int = 0,
    var secondTeamScore: Int = 0,
    var firstTeamPKScore: Int = 0,
    var secondTeamPKScore: Int = 0,
    var loserTeam: String? = null,
    var winnerTeam: String? = null
)
