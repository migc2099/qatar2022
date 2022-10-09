package com.migc.qatar2022.domain.use_case.firebase

import com.google.firebase.auth.FirebaseUser
import com.migc.qatar2022.domain.repository.AuthRepository

class GetFirebaseUserUseCase(
    private val repository: AuthRepository
) {

    operator fun invoke(): FirebaseUser? {
        return repository.firebaseUser
    }

}