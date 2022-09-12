package com.migc.qatar2022.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.migc.qatar2022.common.Constants.GROUPS_TABLE

@Entity(tableName = GROUPS_TABLE)
data class GroupEntity(
    @PrimaryKey
    val groupId: String,
    val groupName: String
)