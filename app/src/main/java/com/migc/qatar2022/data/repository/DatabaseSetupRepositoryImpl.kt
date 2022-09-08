package com.migc.qatar2022.data.repository

import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.domain.repository.DatabaseSetupRepository

class DatabaseSetupRepositoryImpl(
    private val qatarDatabase: QatarDatabase
) : DatabaseSetupRepository {

    override suspend fun setupGroupsFixture() {
        qatarDatabase.fixtureDao.insertFixture(TeamsData.groupsFixture)
    }

}