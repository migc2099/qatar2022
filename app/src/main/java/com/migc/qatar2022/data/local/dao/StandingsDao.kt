package com.migc.qatar2022.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.migc.qatar2022.data.local.entity.StandingsEntity

@Dao
interface StandingsDao {

    @Insert
    fun insertStandings(standings: List<StandingsEntity>)

    @Update
    fun updateStandings(standings: List<StandingsEntity>)

    @Query("SELECT * FROM standings_table WHERE countryId=:countryId")
    suspend fun getStandingByCountryId(countryId: String)

    @Query("SELECT * FROM standings_table WHERE groupKey=:group")
    suspend fun getStandingsByGroup(group: String)

    @Query("SELECT * FROM standings_table WHERE groupKey=:group AND groupPosition=:position")
    suspend fun getStandingByGroupPosition(group: String, position: Int)

}