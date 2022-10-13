package com.migc.qatar2022.domain.use_case.firebase

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.repository.AuthRepository

class SignInWithGoogleUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(googleCredential: AuthCredential): Resource<FirebaseUser?> {
        return authRepository.firebaseSignInWithGoogle(googleCredential)
    }

}