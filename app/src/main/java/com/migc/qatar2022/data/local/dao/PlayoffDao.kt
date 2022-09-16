package com.migc.qatar2022.data.local.dao

import androidx.room.*
import com.migc.qatar2022.data.local.entity.PlayoffEntity

@Dao
interface PlayoffDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun resetAllPlayoffs(playoffs: List<PlayoffEntity>)

    @Query("SELECT * FROM playoffs_table WHERE roundKey=:roundKey")
    suspend fun getPlayoffByRoundKey(roundKey: Int): PlayoffEntity

    @Query("SELECT * FROM playoffs_table WHERE roundKey BETWEEN :startRound AND :endRound")
    suspend fun getPlayoffsByRound(startRound: Int, endRound: Int): List<PlayoffEntity>

    @Query("UPDATE OR IGNORE playoffs_table SET firstTeamId=:teamId WHERE roundKey=:roundKey")
    suspend fun updateFirstTeam(roundKey: Int, teamId: String): Int

    @Query("UPDATE OR IGNORE playoffs_table SET secondTeamId=:teamId WHERE roundKey=:roundKey")
    suspend fun updateSecondTeam(roundKey: Int, teamId: String): Int

    @Update
    suspend fun updateGameResults(playoff: PlayoffEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePlayoffs(playoffs: List<PlayoffEntity>)

}