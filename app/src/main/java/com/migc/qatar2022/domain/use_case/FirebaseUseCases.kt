package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.firebase.*

class FirebaseUseCases(
    val getFirebaseAuthUseCase: GetFirebaseUserUseCase,
    val signInAnonymouslyUseCase: SignInAnonymouslyUseCase,
    val oneTapSignInUseCase: OneTapSignInUseCase,
    val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    val signOutUseCase: SignOutUseCase,
    val oneTapSignOutUseCase: OneTapSignOutUseCase,
    val revokeAccessUseCase: RevokeAccessUseCase
)