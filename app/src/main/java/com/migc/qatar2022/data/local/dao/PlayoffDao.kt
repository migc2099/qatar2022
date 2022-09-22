package com.migc.qatar2022.data.local.dao

import androidx.room.*
import com.migc.qatar2022.data.local.entity.PlayoffEntity

@Dao
interface PlayoffDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun resetAllPlayoffs(playoffs: List<PlayoffEntity>)

    @Query("SELECT * FROM playoffs_table WHERE roundKey=:roundKey")
    suspend fun getPlayoffByRoundKey(roundKey: Int): PlayoffEntity

    @Query("SELECT * FROM playoffs_table")
    suspend fun getAllPlayoffs(): List<PlayoffEntity>

    @Query("UPDATE OR IGNORE playoffs_table SET firstTeamId=:teamId WHERE roundKey=:roundKey")
    suspend fun updateFirstTeam(roundKey: Int, teamId: String): Int

    @Query("UPDATE OR IGNORE playoffs_table SET secondTeamId=:teamId WHERE roundKey=:roundKey")
    suspend fun updateSecondTeam(roundKey: Int, teamId: String): Int

    @Query("UPDATE OR REPLACE playoffs_table SET winnerTeam=:teamId WHERE roundKey=:roundKey")
    suspend fun updateWinnerTeam(roundKey: Int, teamId: String)

    @Query("UPDATE OR REPLACE playoffs_table SET loserTeam=:teamId WHERE roundKey=:roundKey")
    suspend fun updateLoserTeam(roundKey: Int, teamId: String)

    @Update
    suspend fun updateGameResults(playoff: PlayoffEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePlayoffs(playoffs: List<PlayoffEntity>)

    @Query("SELECT * FROM playoffs_table WHERE loserTeam=:teamId OR winnerTeam=:teamId")
    suspend fun getFinishedPlayoffsByTeamId(teamId: String): List<PlayoffEntity>

}