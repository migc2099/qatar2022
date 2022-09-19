package com.migc.qatar2022.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.migc.qatar2022.common.Constants.STANDINGS_TABLE

@Entity(tableName = STANDINGS_TABLE)
data class StandingsEntity(
    @PrimaryKey
    val teamId: String,
    val groupKey: String,
    val groupPosition: Int = 1,
    val gamesPlayed: Int = 0,
    val wins: Int = 0,
    val draws: Int = 0,
    val loses: Int = 0,
    val goalsInFavor: Int = 0,
    val goalsAgainst: Int = 0,
    val points: Int = 0,
    var maxStage: Int = 3
)
