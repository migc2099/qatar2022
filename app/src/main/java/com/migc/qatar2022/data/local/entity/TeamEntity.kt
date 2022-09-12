package com.migc.qatar2022.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.migc.qatar2022.common.Constants.TEAMS_TABLE

@Entity(tableName = TEAMS_TABLE)
data class TeamEntity(
    @PrimaryKey(autoGenerate = false)
    var teamId: String,
    var name: String,
    var groupKey: String,
    var flagLocation: Int
)
