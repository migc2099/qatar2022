package com.migc.qatar2022.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    private val LOG_TAG = "AuthRepositoryImpl"

    override var firebaseUser = auth.currentUser

    override suspend fun signInAnonymously(): FirebaseUser? {
        return suspendCancellableCoroutine { continuation ->


            auth.signInAnonymously()
                .addOnSuccessListener {
                    Log.d(LOG_TAG, "signInAnonymously ${auth.currentUser!!.uid}")
                    firebaseUser = auth.currentUser
                    continuation.resume(firebaseUser)
                }
                .addOnFailureListener { e ->
                    Log.e(LOG_TAG, "signInAnonymously ${e.message}")
                    continuation.resumeWithException(e)
                }
        }
    }

    override suspend fun signOut() {
        auth.signOut()
        firebaseUser = null
    }

}