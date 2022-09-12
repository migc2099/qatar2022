package com.migc.qatar2022.domain.use_case.datastore

import com.migc.qatar2022.domain.repository.DataStoreOpsRepository
import com.migc.qatar2022.domain.repository.DatabaseSetupRepository
import kotlinx.coroutines.flow.Flow

class ReadOnTeamsSetupUseCase(
    private val repository: DataStoreOpsRepository
) {

    operator fun invoke(): Flow<Boolean> {
        return repository.readOnTeamsSetupCompleted()
    }
}