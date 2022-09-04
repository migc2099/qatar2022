package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.domain.model.TeamStat

interface StandingsRepository {

    fun setUpTeams(): List<TeamStat>
    suspend fun insertTeams(teamsStats: List<TeamStat>)
    suspend fun getTeamsByGroup(group: String): List<TeamStat>
    suspend fun getTeamByGroupPosition(groupKey: String, position: Int): TeamStat

}