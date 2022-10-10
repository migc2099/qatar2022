package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.model.Team

interface PlayoffsRepository {

    fun setPlayoffs(): List<Playoff>
    suspend fun getPlayoffByRoundKey(roundKey: Int): Playoff
    suspend fun getAllPlayoffs(): List<Playoff>
    suspend fun resetPlayoffs()
    suspend fun updatePlayoffResults(playoff: Playoff): Int
    suspend fun updateFirstTeam(roundKey: Int, teamId: String): Int
    suspend fun updateSecondTeam(roundKey: Int, teamId: String): Int
    suspend fun updateWinnerTeam(roundKey: Int, teamId: String)
    suspend fun updateLoserTeam(roundKey: Int, teamId: String)
    suspend fun updatePlayoffs(playoffs: List<Playoff>)
    suspend fun getPlayoffsByTeamId(teamId: String): List<Playoff>
    suspend fun uploadWinners(teams: List<Team>): Boolean

}