package com.migc.qatar2022.domain.use_case.group_details

import android.util.Log
import com.migc.qatar2022.data.local.mapper.toFixture
import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.domain.repository.FixtureRepository

class GetFixtureByGroupUseCase(
    private val repository: FixtureRepository
) {

    suspend operator fun invoke(group: String): List<Fixture> {
        val groupMatches = repository
            .getFixtureByGroup(group).map {
                Log.d("GetFixtureByGroupUseCase", it.toString())
                it.toFixture()
            }
        return groupMatches
    }

}