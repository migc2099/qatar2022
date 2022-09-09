package com.migc.qatar2022.domain.use_case.datastore

import com.migc.qatar2022.domain.repository.DataStoreOpsRepository

class SaveOnStandingsSetupUseCase(
    private val dataStoreOpsRepository: DataStoreOpsRepository
) {

    suspend operator fun invoke(completed: Boolean) {
        dataStoreOpsRepository.saveOnStandingsSetupCompleted(completed)
    }

}