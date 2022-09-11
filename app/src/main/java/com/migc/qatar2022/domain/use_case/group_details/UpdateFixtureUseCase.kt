package com.migc.qatar2022.domain.use_case.group_details

import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.domain.repository.FixtureRepository

class UpdateFixtureUseCase(
    private val fixtureRepository: FixtureRepository
) {

    suspend operator fun invoke(results: List<Fixture>){
        fixtureRepository.updateFixture(results)
    }

}