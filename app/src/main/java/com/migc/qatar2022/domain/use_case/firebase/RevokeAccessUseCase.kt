package com.migc.qatar2022.domain.use_case.firebase

import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.repository.AuthRepository

class RevokeAccessUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): Resource<Boolean> {
        return repository.revokeAccessGoogle()
    }

}