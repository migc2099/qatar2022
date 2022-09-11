package com.migc.qatar2022.data.repository

import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.data.local.entity.FixtureEntity
import com.migc.qatar2022.data.local.mapper.toFixtureEntity
import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.domain.repository.FixtureRepository
import javax.inject.Inject

class FixtureRepositoryImpl @Inject constructor(
//    private val fixtureApi: FixtureApi,
    private val qatarDatabase: QatarDatabase
) : FixtureRepository {

//    override suspend fun getMatchesByGroup(group: String): List<FixtureDto> {
//        return fixtureApi.getFixture()
//    }

    override suspend fun getFixtureByGroup(group: String): List<FixtureEntity> {
        return qatarDatabase.fixtureDao.getGroupMatches(group)
    }

    override suspend fun updateFixture(results: List<Fixture>) {
        val fixtureEntity = results.map {
            it.toFixtureEntity()
        }
        qatarDatabase.fixtureDao.updateFixture(fixtureEntity)
    }

    override suspend fun updateSingleFixture(match: Fixture) {
        val fixtureEntity = match.toFixtureEntity()
        qatarDatabase.fixtureDao.updateSingleFixture(fixtureEntity)
    }
}