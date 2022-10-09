package com.migc.qatar2022.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.migc.qatar2022.data.repository.AuthRepositoryImpl
import com.migc.qatar2022.domain.repository.AuthRepository
import com.migc.qatar2022.domain.use_case.FirebaseUseCases
import com.migc.qatar2022.domain.use_case.firebase.GetFirebaseUserUseCase
import com.migc.qatar2022.domain.use_case.firebase.SignInAnonymouslyUseCase
import com.migc.qatar2022.domain.use_case.firebase.SignOutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth
    ): AuthRepository {
        return AuthRepositoryImpl(
            auth = auth
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseUseCases(repository: AuthRepository): FirebaseUseCases {
        return FirebaseUseCases(
            getFirebaseAuthUseCase = GetFirebaseUserUseCase(repository),
            signInAnonymouslyUseCase = SignInAnonymouslyUseCase(repository),
            signOutUseCase = SignOutUseCase(repository)
        )
    }

}