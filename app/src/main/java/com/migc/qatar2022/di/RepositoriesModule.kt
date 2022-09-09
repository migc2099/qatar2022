package com.migc.qatar2022.di

import android.content.Context
import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.data.repository.*
import com.migc.qatar2022.domain.repository.*
import com.migc.qatar2022.domain.use_case.*
import com.migc.qatar2022.domain.use_case.database_setup.SetGroupsFixtureUseCase
import com.migc.qatar2022.domain.use_case.datastore.ReadOnFixtureSetupUseCase
import com.migc.qatar2022.domain.use_case.datastore.SaveOnFixtureSetupUseCase
import com.migc.qatar2022.domain.use_case.finals.EnterKnockOutResultUseCase
import com.migc.qatar2022.domain.use_case.finals.GetMatchByRoundUseCase
import com.migc.qatar2022.domain.use_case.finals.SetupFinalsUseCase
import com.migc.qatar2022.domain.use_case.group_details.GetFixtureByGroupUseCase
import com.migc.qatar2022.domain.use_case.standings.GetTeamByGroupPositionUseCase
import com.migc.qatar2022.domain.use_case.standings.GetTeamsByGroupUseCase
import com.migc.qatar2022.domain.use_case.standings.SetupStandingsUseCase
import com.migc.qatar2022.domain.use_case.standings.UpdateStandingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideFixtureRepository(database: QatarDatabase): FixtureRepository {
        return FixtureRepositoryImpl(qatarDatabase = database)
    }

    @Provides
    @Singleton
    fun provideGroupDetailsUseCases(repository: FixtureRepository): GroupDetailsUseCases {
        return GroupDetailsUseCases(
//            getMatchesByGroupUseCase = GetMatchesByGroupUseCase(repository),
            getFixtureByGroupUseCase = GetFixtureByGroupUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDatabaseSetupRepository(database: QatarDatabase):DatabaseSetupRepository{
        return DatabaseSetupRepositoryImpl(qatarDatabase = database)
    }

    @Provides
    @Singleton
    fun provideDatabaseSetupUseCases(repository: DatabaseSetupRepository): DatabaseSetupUseCases {
        return DatabaseSetupUseCases(
            setGroupsFixtureUseCase = SetGroupsFixtureUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreOpsRepository(
        @ApplicationContext context: Context):DataStoreOpsRepository{
        return DataStoreOpsRepositoryImpl(
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreUseCases(repository: DataStoreOpsRepository):DataStoreUseCases{
        return DataStoreUseCases(
            saveOnFixtureSetupUseCase = SaveOnFixtureSetupUseCase(repository),
            readOnFixtureSetupUseCase = ReadOnFixtureSetupUseCase(repository)
        )
    }

}