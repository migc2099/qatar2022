package com.migc.qatar2022.domain.use_case.datastore

import com.migc.qatar2022.domain.repository.DataStoreOpsRepository
import kotlinx.coroutines.flow.Flow

class ReadOnStandingsSetupUseCase(
    private val dataStoreOpsRepository: DataStoreOpsRepository
) {

    operator fun invoke(): Flow<Boolean> {
        return dataStoreOpsRepository.readOnStandingsSetupCompleted()
    }

}