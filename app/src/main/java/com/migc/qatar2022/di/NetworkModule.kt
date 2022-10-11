package com.migc.qatar2022.di

import android.content.Context
import com.migc.qatar2022.common.Constants.FIXTURE_BASE_URL
import com.migc.qatar2022.data.remote.FixtureApi
import com.migc.qatar2022.data.repository.NetworkRepositoryImpl
import com.migc.qatar2022.domain.repository.NetworkRepository
import com.migc.qatar2022.domain.use_case.NetworkUseCases
import com.migc.qatar2022.domain.use_case.network.CheckIfInternetAvailableUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFixtureRetrofitInstance(): FixtureApi {
        return Retrofit.Builder()
            .baseUrl(FIXTURE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FixtureApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkRepository(
        @ApplicationContext context: Context
    ): NetworkRepository {
        return NetworkRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideNetworkUseCases(
        repository: NetworkRepository
    ): NetworkUseCases {
        return NetworkUseCases(
            checkIfInternetAvailableUseCase = CheckIfInternetAvailableUseCase(repository)
        )
    }

//    @Provides
//    @Singleton
//    fun provideRankingRetrofitInstance(): CountriesInfoApi {
//        return Retrofit.Builder()
//            .baseUrl(RANKINGS_BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(CountriesInfoApi::class.java)
//    }

}