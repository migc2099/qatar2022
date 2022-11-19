package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.domain.model.Fixture
import kotlinx.coroutines.flow.Flow

interface FixtureRepository {

    fun getFixtureByGroup(group: String): Flow<List<Fixture>>

    suspend fun updateFixture(results: List<Fixture>)

    suspend fun updateSingleFixture(match: Fixture)

}