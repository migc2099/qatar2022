package com.migc.qatar2022.domain.use_case.datastore

import com.migc.qatar2022.domain.repository.DataStoreOpsRepository
import kotlinx.coroutines.flow.Flow

class ReadOnGroupsSetupUseCase(
    private val repository: DataStoreOpsRepository
) {

    operator fun invoke(): Flow<Boolean> {
        return repository.readOnGroupsSetupCompleted()
    }

}