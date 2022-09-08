package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.data.local.entity.FixtureEntity

interface FixtureRepository {

//    suspend fun getMatchesByGroup(group: String): List<FixtureDto>

    suspend fun getFixtureByGroup(group: String): List<FixtureEntity>

}