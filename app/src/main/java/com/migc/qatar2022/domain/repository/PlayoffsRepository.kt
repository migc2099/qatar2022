package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.domain.model.Playoff

interface PlayoffsRepository {

    fun setPlayoffs(): List<Playoff>
    suspend fun getPlayoffByRoundKey(roundKey: Int): Playoff
    suspend fun resetPlayoffs()
    suspend fun updatePlayoff(playoff: Playoff): Int
    suspend fun updateFirstTeam(roundKey: Int, teamId: String):Int
    suspend fun updateSecondTeam(roundKey: Int, teamId: String):Int
    suspend fun updatePlayoffs(playoffs: List<Playoff>)

}