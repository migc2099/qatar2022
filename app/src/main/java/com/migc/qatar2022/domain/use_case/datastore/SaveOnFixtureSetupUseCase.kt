package com.migc.qatar2022.domain.use_case.datastore

import com.migc.qatar2022.domain.repository.DataStoreOpsRepository

class SaveOnFixtureSetupUseCase(
    private val dataStoreOperations: DataStoreOpsRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        dataStoreOperations.saveOnFixtureSetupCompleted(completed)
    }
}