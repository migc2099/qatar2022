package com.migc.qatar2022.domain.use_case.firebase

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.repository.AuthRepository

class OneTapSignInUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Resource<BeginSignInResult> {
        return authRepository.oneTapSignInWithGoogle()
    }

}