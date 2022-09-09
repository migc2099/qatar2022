package com.migc.qatar2022.data.local.dao

import androidx.room.*
import com.migc.qatar2022.data.local.entity.StandingsEntity

@Dao
interface StandingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStandings(standings: List<StandingsEntity>): List<Long>

    @Update
    fun updateStandings(standings: List<StandingsEntity>)

    @Query("SELECT * FROM standings_table WHERE teamId=:teamId")
    suspend fun getStandingByCountryId(teamId: String): StandingsEntity

    @Query("SELECT * FROM standings_table WHERE groupKey=:group")
    suspend fun getStandingsByGroup(group: String): List<StandingsEntity>

    @Query("SELECT * FROM standings_table WHERE groupKey=:group AND groupPosition=:position LIMIT 1")
    suspend fun getStandingByGroupPosition(group: String, position: Int): StandingsEntity

}

