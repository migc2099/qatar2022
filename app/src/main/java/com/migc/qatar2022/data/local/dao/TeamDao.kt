package com.migc.qatar2022.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.migc.qatar2022.data.local.entity.TeamEntity

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamEntity>): List<Long>

    @Query("SELECT * FROM teams_table WHERE teamId=:id")
    suspend fun getTeamById(id: String): TeamEntity

}