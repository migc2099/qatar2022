package com.migc.qatar2022.data.repository

import android.util.Log
import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.data.local.mapper.toGroup
import com.migc.qatar2022.data.local.mapper.toStandingsEntity
import com.migc.qatar2022.data.local.mapper.toTeam
import com.migc.qatar2022.data.local.mapper.toTeamsStat
import com.migc.qatar2022.domain.model.Group
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.domain.model.TeamStat
import com.migc.qatar2022.domain.repository.StandingsRepository
import javax.inject.Inject

class StandingsRepositoryImpl @Inject constructor(
    private val qatarDatabase: QatarDatabase
) : StandingsRepository {

    private val LOG_TAG = "StandingsRepositoryImpl"

    override fun setUpTeams(): List<TeamStat> {
        return listOf(
            TeamStat("QAT", "A"),
            TeamStat("ECU", "A"),
            TeamStat("SEN", "A"),
            TeamStat("NET", "A"),

            TeamStat("ENG", "B"),
            TeamStat("IRA", "B"),
            TeamStat("USA", "B"),
            TeamStat("WAL", "B"),

            TeamStat("ARG", "C"),
            TeamStat("SAU", "C"),
            TeamStat("MEX", "C"),
            TeamStat("POL", "C"),

            TeamStat("FRA", "D"),
            TeamStat("DEN", "D"),
            TeamStat("TUN", "D"),
            TeamStat("AUS", "D"),

            TeamStat("SPA", "E"),
            TeamStat("GER", "E"),
            TeamStat("JAP", "E"),
            TeamStat("COS", "E"),

            TeamStat("BEL", "F"),
            TeamStat("CAN", "F"),
            TeamStat("MOR", "F"),
            TeamStat("CRO", "F"),

            TeamStat("BRA", "G"),
            TeamStat("SER", "G"),
            TeamStat("SWI", "G"),
            TeamStat("CAM", "G"),

            TeamStat("POR", "H"),
            TeamStat("GHA", "H"),
            TeamStat("URU", "H"),
            TeamStat("KOR", "H")
        )
    }

    override suspend fun insertTeams(teamsStats: List<TeamStat>) {
        val standings = teamsStats.map {
            it.toStandingsEntity()
        }
        Log.d(LOG_TAG, "inserting $standings")
        qatarDatabase.standingsDao.insertStandings(standings)
    }

    override suspend fun getTeamsByGroup(group: String): List<TeamStat> {
        val teamsStats = qatarDatabase.standingsDao.getStandingsByGroup(group).map {
            it.toTeamsStat()
        }
        Log.d(LOG_TAG, "retrieving teams from db $teamsStats")
        return teamsStats
    }

    override suspend fun getTeamByGroupPosition(groupKey: String, position: Int): TeamStat {
        return qatarDatabase.standingsDao.getStandingByGroupPosition(
            group = groupKey,
            position = position
        ).toTeamsStat()
    }

    override fun getStatsPerGroup(): Map<Group, List<Team>> {
        val statsMap: MutableMap<Group, List<Team>> = mutableMapOf()
        val standingsMap = qatarDatabase.standingsDao.getStandingsPerGroup()
        Log.d("StandingsRepositoryImpl", "getStatsPerGroup() $standingsMap")
        standingsMap.forEach { standing ->
            val teamsStats = standing.value.map {
                it.toTeam()
            }
            statsMap[standing.key.toGroup()] = teamsStats
        }
        Log.d("StandingsRepositoryImpl", "statsMap $statsMap")
        return statsMap
    }

}