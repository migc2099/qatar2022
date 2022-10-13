package com.migc.qatar2022.domain.use_case.firebase

import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignOutUseCase(
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            authRepository.signOut()
            emit(Resource.Success(data = true))
        } catch (e: Exception) {
            emit(Resource.Error(message = Constants.UNEXPECTED_EXCEPTION_ERROR_MESSAGE))
        }
    }

}