package com.migc.qatar2022.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    var firebaseUser: FirebaseUser?

    suspend fun signInAnonymously(): FirebaseUser?

    suspend fun signOut()

}