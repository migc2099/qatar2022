package com.migc.qatar2022.data.repository

import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.data.local.mapper.toFixture
import com.migc.qatar2022.data.local.mapper.toFixtureEntity
import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.domain.repository.FixtureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FixtureRepositoryImpl @Inject constructor(
    private val qatarDatabase: QatarDatabase
) : FixtureRepository {

    override fun getFixtureByGroup(group: String): Flow<List<Fixture>> {
        return qatarDatabase.fixtureDao.getGroupMatches(group).map { fixtureList ->
            fixtureList.map {
                it.toFixture()
            }
        }
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