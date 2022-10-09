package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.firebase.GetFirebaseUserUseCase
import com.migc.qatar2022.domain.use_case.firebase.SignInAnonymouslyUseCase
import com.migc.qatar2022.domain.use_case.firebase.SignOutUseCase

class FirebaseUseCases(
    val getFirebaseAuthUseCase: GetFirebaseUserUseCase,
    val signInAnonymouslyUseCase: SignInAnonymouslyUseCase,
    val signOutUseCase: SignOutUseCase
)