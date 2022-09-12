package com.migc.qatar2022.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.migc.qatar2022.data.local.entity.GroupEntity

@Dao
interface GroupDao {

    @Insert
    suspend fun insertGroups(groups: List<GroupEntity>): List<Long>

}