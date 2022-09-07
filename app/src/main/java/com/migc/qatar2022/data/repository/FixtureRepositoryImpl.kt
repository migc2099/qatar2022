package com.migc.qatar2022.data.repository

import com.migc.qatar2022.data.remote.FixtureApi
import com.migc.qatar2022.data.remote.dto.FixtureDto
import com.migc.qatar2022.domain.repository.FixtureRepository
import javax.inject.Inject

class FixtureRepositoryImpl @Inject constructor(
    private val fixtureApi: FixtureApi
) : FixtureRepository {

    override suspend fun getMatchesByGroup(group: String): List<FixtureDto> {
        return fixtureApi.getFixture()
    }

}