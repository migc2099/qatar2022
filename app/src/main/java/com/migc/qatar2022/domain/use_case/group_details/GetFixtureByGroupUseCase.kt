package com.migc.qatar2022.domain.use_case.group_details

import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.domain.repository.FixtureRepository
import kotlinx.coroutines.flow.*

class GetFixtureByGroupUseCase(
    private val repository: FixtureRepository
) {

    operator fun invoke(group: String): Flow<List<Fixture>> {
        return repository.getFixtureByGroup(group)
    }

}