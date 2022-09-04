package com.migc.qatar2022.di

import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.data.repository.FinalsRepositoryImpl
import com.migc.qatar2022.data.repository.StandingsRepositoryImpl
import com.migc.qatar2022.domain.repository.FinalsRepository
import com.migc.qatar2022.domain.repository.StandingsRepository
import com.migc.qatar2022.domain.use_case.FinalsUseCases
import com.migc.qatar2022.domain.use_case.StandingsUseCases
import com.migc.qatar2022.domain.use_case.finals.EnterKnockOutResultUseCase
import com.migc.qatar2022.domain.use_case.finals.GetMatchByRoundUseCase
import com.migc.qatar2022.domain.use_case.finals.SetupFinalsUseCase
import com.migc.qatar2022.domain.use_case.standings.GetTeamByGroupPositionUseCase
import com.migc.qatar2022.domain.use_case.standings.GetTeamsByGroupUseCase
import com.migc.qatar2022.domain.use_case.standings.SetupStandingsUseCase
import com.migc.qatar2022.domain.use_case.standings.UpdateStandingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideStandingsRepository(database: QatarDatabase): StandingsRepository {
        return StandingsRepositoryImpl(qatarDatabase = database)
    }

    @Provides
    @Singleton
    fun provideStandingsUseCases(repository: StandingsRepository): StandingsUseCases {
        return StandingsUseCases(
            setupStandingsUseCase = SetupStandingsUseCase(repository),
            getTeamsByGroupUseCase = GetTeamsByGroupUseCase(repository),
            getTeamByGroupPositionUseCase = GetTeamByGroupPositionUseCase(repository),
            updateStandingsUseCase = UpdateStandingsUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideFinalsRepository(database: QatarDatabase): FinalsRepository {
        return FinalsRepositoryImpl(qatarDatabase = database)
    }

    @Provides
    @Singleton
    fun provideFinalsUseCases(repository: FinalsRepository): FinalsUseCases {
        return FinalsUseCases(
            enterKnockOutResultUseCase = EnterKnockOutResultUseCase(repository),
            getMatchByRoundUseCase = GetMatchByRoundUseCase(repository),
            setupFinalsUseCase = SetupFinalsUseCase(repository)
        )
    }

}