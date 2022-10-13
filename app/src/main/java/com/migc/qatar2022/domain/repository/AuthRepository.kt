package com.migc.qatar2022.domain.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.migc.qatar2022.common.Resource

interface AuthRepository {
    var firebaseUser: FirebaseUser?

    suspend fun signInAnonymously(): FirebaseUser?

    suspend fun signOut()

    suspend fun oneTapSignInWithGoogle(): Resource<BeginSignInResult>

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Resource<FirebaseUser?>

    suspend fun signOutGoogle(): Resource<Boolean>

    suspend fun revokeAccessGoogle(): Resource<Boolean>

}