package com.migc.qatar2022.data.local.dao

import androidx.room.*
import com.migc.qatar2022.data.local.entity.PlayoffEntity

@Dao
interface PlayoffDao {

    @Insert
    suspend fun resetAllPlayoffs(playoffs: List<PlayoffEntity>)

    @Query("SELECT * FROM playoffs_table WHERE roundKey=:roundKey")
    suspend fun getMatch(roundKey: Int): PlayoffEntity

    @Query("UPDATE playoffs_table SET firstTeamId=:teamId WHERE roundKey=:roundKey")
    suspend fun updateFirstTeam(roundKey: Int, teamId: String): Int

    @Query("UPDATE playoffs_table SET secondTeamId=:teamId WHERE roundKey=:roundKey")
    suspend fun updateSecondTeam(roundKey: Int, teamId: String): Int

    @Update
    suspend fun updatePlayoff(playoff: PlayoffEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePlayoffs(playoffs: List<PlayoffEntity>)

}