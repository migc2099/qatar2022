package com.migc.qatar2022.data.repository

import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.migc.qatar2022.common.Constants.SIGN_IN_EXCEPTION_TRY_AGAIN_MESSAGE
import com.migc.qatar2022.common.Constants.SIGN_IN_REQUEST
import com.migc.qatar2022.common.Constants.SIGN_UP_REQUEST
import com.migc.qatar2022.common.Constants.UNEXPECTED_EXCEPTION_ERROR_MESSAGE
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.repository.AuthRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    private var signInClient: GoogleSignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
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

    override suspend fun oneTapSignInWithGoogle(): Resource<BeginSignInResult> {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Log.d("AuthRepo", "signInResult $signInResult")
            Resource.Success(data = signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Log.d("AuthRepo", "signUpResult $signUpResult")
                Resource.Success(signUpResult)
            } catch (e: ApiException) {
                Log.e("oneTapSignInWithGoogle()", "status: ${e.status}")
                Log.e("oneTapSignInWithGoogle()", "statusCode: ${e.statusCode}")
                Log.e("oneTapSignInWithGoogle()", "message: $e.message")
                Resource.Error(message = SIGN_IN_EXCEPTION_TRY_AGAIN_MESSAGE)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): Resource<FirebaseUser?> {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
//                addUserToFirestore()
            }
            firebaseUser = authResult.user
            Resource.Success(firebaseUser)
        } catch (e: Exception) {
            Log.e("firebaseSignInWithGoogle()", e.message.toString())
            Resource.Error(message = SIGN_IN_EXCEPTION_TRY_AGAIN_MESSAGE)
        }
    }

    override suspend fun signOutGoogle(): Resource<Boolean> {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
            firebaseUser = null
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(message = UNEXPECTED_EXCEPTION_ERROR_MESSAGE)
        }
    }

    override suspend fun revokeAccessGoogle(): Resource<Boolean> {
        return try {
            auth.currentUser?.apply {
//                db.collection(USERS).document(uid).delete().await()
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
                delete().await()
            }
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(message = UNEXPECTED_EXCEPTION_ERROR_MESSAGE)
        }
    }

}