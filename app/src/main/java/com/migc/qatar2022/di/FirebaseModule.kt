package com.migc.qatar2022.di

import android.app.Application
import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.migc.qatar2022.R
import com.migc.qatar2022.common.Constants.SIGN_IN_REQUEST
import com.migc.qatar2022.common.Constants.SIGN_UP_REQUEST
import com.migc.qatar2022.data.repository.AuthRepositoryImpl
import com.migc.qatar2022.domain.repository.AuthRepository
import com.migc.qatar2022.domain.use_case.FirebaseUseCases
import com.migc.qatar2022.domain.use_case.firebase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideOneTapClient(
        @ApplicationContext context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Named(SIGN_IN_REQUEST)
    fun provideSignInRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(true)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

    @Provides
    @Named(SIGN_UP_REQUEST)
    fun provideSignUpRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()

    @Provides
    fun provideGoogleSignInOptions(
        app: Application
    ) = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(app.getString(R.string.web_client_id))
        .requestEmail()
        .build()

    @Provides
    fun provideGoogleSignInClient(
        app: Application,
        options: GoogleSignInOptions
    ) = GoogleSignIn.getClient(app, options)

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        oneTapClient: SignInClient,
        signInClient: GoogleSignInClient,
        @Named(SIGN_IN_REQUEST)
        signInRequest: BeginSignInRequest,
        @Named(SIGN_UP_REQUEST)
        signUpRequest: BeginSignInRequest
    ): AuthRepository {
        return AuthRepositoryImpl(
            auth = auth,
            oneTapClient = oneTapClient,
            signInClient = signInClient,
            signInRequest = signInRequest,
            signUpRequest = signUpRequest
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseUseCases(repository: AuthRepository): FirebaseUseCases {
        return FirebaseUseCases(
            getFirebaseAuthUseCase = GetFirebaseUserUseCase(repository),
            signInAnonymouslyUseCase = SignInAnonymouslyUseCase(repository),
            oneTapSignInUseCase = OneTapSignInUseCase(repository),
            signInWithGoogleUseCase = SignInWithGoogleUseCase(repository),
            signOutUseCase = SignOutUseCase(repository),
            oneTapSignOutUseCase = OneTapSignOutUseCase(repository),
            revokeAccessUseCase = RevokeAccessUseCase(repository)
        )
    }

}