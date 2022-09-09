package com.migc.qatar2022.data.repository

import android.util.Log
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.domain.repository.DatabaseSetupRepository

class DatabaseSetupRepositoryImpl(
    private val qatarDatabase: QatarDatabase
) : DatabaseSetupRepository {

    override suspend fun setupGroupsFixture(): Boolean {
        val inserted: List<Long> = qatarDatabase.fixtureDao.insertFixture(TeamsData.groupsFixture)
        Log.d("DatabaseSetupRepositoryImpl", "${inserted.size} rows inserted")
        return inserted.size == 48
    }

}