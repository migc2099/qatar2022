package com.migc.qatar2022.data.repository

import android.util.Log
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

}