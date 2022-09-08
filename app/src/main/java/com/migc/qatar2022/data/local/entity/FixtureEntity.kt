package com.migc.qatar2022.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.migc.qatar2022.common.Constants.FIXTURE_TABLE

@Entity(tableName = FIXTURE_TABLE)
data class FixtureEntity(
    @PrimaryKey
    val matchNumber: Int,
    val roundNumber: Int,
    val groupKey: String,
    val date: Int,
    val location: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamScore: Int = 0,
    val awayTeamScore: Int = 0
)
