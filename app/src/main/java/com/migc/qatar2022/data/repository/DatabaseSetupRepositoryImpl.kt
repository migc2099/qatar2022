package com.migc.qatar2022.data.repository

import android.util.Log
import com.migc.qatar2022.common.Constants.TOTAL_GROUPS
import com.migc.qatar2022.common.Constants.TOTAL_GROUP_MATCHES
import com.migc.qatar2022.common.Constants.TOTAL_TEAMS
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.domain.repository.DatabaseSetupRepository

class DatabaseSetupRepositoryImpl(
    private val qatarDatabase: QatarDatabase
) : DatabaseSetupRepository {

    override suspend fun setupGroupsFixture(): Boolean {
        val inserted: List<Long> = qatarDatabase.fixtureDao.insertFixture(TeamsData.groupsFixture)
        Log.d("DatabaseSetupRepositoryImpl", "setupGroupsFixture() ${inserted.size} rows inserted")
        return inserted.size == TOTAL_GROUP_MATCHES
    }

    override suspend fun setupStandings(): Boolean {
        val inserted = qatarDatabase.standingsDao.insertStandings(TeamsData.standings)
        Log.d("DatabaseSetupRepositoryImpl", "setupStandings() ${inserted.size} rows inserted")
        return inserted.size == TOTAL_TEAMS
    }

    override suspend fun setupGroups(): Boolean {
        val inserted = qatarDatabase.groupDao.insertGroups(TeamsData.groups)
        Log.d("DatabaseSetupRepositoryImpl", "setupGroups() ${inserted.size} rows inserted")
        return inserted.size == TOTAL_GROUPS
    }

    override suspend fun setupTeams(): Boolean {
        val inserted = qatarDatabase.teamDao.insertTeams(TeamsData.teams)
        Log.d("DatabaseSetupRepositoryImpl", "setupTeams() ${inserted.size} rows inserted")
        return inserted.size == TOTAL_TEAMS
    }

}