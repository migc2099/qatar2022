package com.migc.qatar2022.domain.use_case.datastore

import com.migc.qatar2022.domain.repository.DataStoreOpsRepository

class SaveOnTeamsSetupUseCase(
    private val repository: DataStoreOpsRepository
) {

    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnTeamsSetupCompleted(completed)
    }

}