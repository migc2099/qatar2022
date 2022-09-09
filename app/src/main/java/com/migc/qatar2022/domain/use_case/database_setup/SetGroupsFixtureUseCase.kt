package com.migc.qatar2022.domain.use_case.database_setup

import com.migc.qatar2022.domain.repository.DatabaseSetupRepository

class SetGroupsFixtureUseCase(
    private val databaseSetupRepository: DatabaseSetupRepository
) {

    suspend operator fun invoke() :Boolean {
        return databaseSetupRepository.setupGroupsFixture()
    }

}