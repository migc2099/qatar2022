package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.data.remote.dto.FixtureDto

interface FixtureRepository {

    suspend fun getMatchesByGroup(group: String): List<FixtureDto>

}