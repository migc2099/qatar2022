package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.domain.model.Group
import com.migc.qatar2022.domain.model.PlayOffTeam
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.domain.model.TeamStat

interface StandingsRepository {

    fun setUpTeams(): List<TeamStat>
    suspend fun insertTeams(teamsStats: List<TeamStat>)
    suspend fun updateTeamStage(teamId: String, stage: Int)
    suspend fun getTeamsByGroup(group: String): List<TeamStat>
    suspend fun getTeamsByGroupPosition(position: Int): List<TeamStat>
    fun getStatsPerGroup(): Map<Group, List<Team>>
    fun checkIfGroupGamesCompleted(groupKey: String): Boolean
//    fun getQualifiedTeamsByGroup(groupKey: String): List<PlayOffTeam>

}