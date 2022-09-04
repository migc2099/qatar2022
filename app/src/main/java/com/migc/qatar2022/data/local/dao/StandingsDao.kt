package com.migc.qatar2022.data.local.dao

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.migc.qatar2022.data.local.entity.StandingsEntity

interface StandingsDao {

    @Insert
    fun insertStandings(vararg standingsEntities: StandingsEntity)

    @Update
    fun updateStandings(vararg standingsEntities: StandingsEntity)

    @Query("SELECT * FROM standings_table WHERE countryId=:countryId")
    suspend fun getStandingByCountryId(countryId: String)

    @Query("SELECT * FROM standings_table WHERE groupKey=:group")
    suspend fun getStandingsByGroup(group: String)

    @Query("SELECT * FROM standings_table WHERE groupKey=:group AND groupPosition=:position")
    suspend fun getStandingByGroupPlace(group: String, position: Int)

}