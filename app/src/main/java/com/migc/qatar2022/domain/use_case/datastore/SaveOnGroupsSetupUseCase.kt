package com.migc.qatar2022.domain.use_case.datastore

import com.migc.qatar2022.domain.repository.DataStoreOpsRepository

class SaveOnGroupsSetupUseCase(
    private val repository: DataStoreOpsRepository
) {

    suspend operator fun invoke(completed: Boolean){
        repository.saveOnGroupsSetupCompleted(completed)
    }

}