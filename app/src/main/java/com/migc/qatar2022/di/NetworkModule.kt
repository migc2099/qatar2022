package com.migc.qatar2022.di

import com.migc.qatar2022.common.Constants.FIXTURE_BASE_URL
import com.migc.qatar2022.data.remote.FixtureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}