package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.domain.model.Fixture
import kotlinx.coroutines.flow.Flow

interface FixtureRepository {

//    suspend fun getMatchesByGroup(group: String): List<FixtureDto>

    fun getFixtureByGroup(group: String): Flow<List<Fixture>>

    suspend fun updateFixture(results: List<Fixture>)

    suspend fun updateSingleFixture(match: Fixture)

}