package com.migc.qatar2022.di

import android.content.Context
import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.data.remote.CountriesInfoApi
import com.migc.qatar2022.data.repository.*
import com.migc.qatar2022.domain.repository.*
import com.migc.qatar2022.domain.use_case.*
import com.migc.qatar2022.domain.use_case.database_setup.SetGroupsFixtureUseCase
import com.migc.qatar2022.domain.use_case.database_setup.SetGroupsUseCase
import com.migc.qatar2022.domain.use_case.database_setup.SetStandingsUseCase
import com.migc.qatar2022.domain.use_case.database_setup.SetTeamsUseCase
import com.migc.qatar2022.domain.use_case.datastore.*
import com.migc.qatar2022.domain.use_case.group_details.CalculatePointsUseCase
import com.migc.qatar2022.domain.use_case.group_details.CalculateWeightedResultUseCase
import com.migc.qatar2022.domain.use_case.group_details.GetFixtureByGroupUseCase
import com.migc.qatar2022.domain.use_case.group_details.UpdateFixtureUseCase
import com.migc.qatar2022.domain.use_case.playoffs.*
import com.migc.qatar2022.domain.use_case.standings.*
import com.migc.qatar2022.domain.use_case.team.GetTeamByIdUseCase
import com.migc.qatar2022.domain.use_case.teams_map.GetCountriesInfoUseCase
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
    fun provideStandingsUseCases(
        repository: StandingsRepository,
        playoffRepository: PlayoffsRepository
    ): StandingsUseCases {
        return StandingsUseCases(
            setupStandingsUseCase = SetupStandingsUseCase(repository),
            getTeamsByGroupUseCase = GetTeamsByGroupUseCase(repository),
            updateStandingsUseCase = UpdateStandingsUseCase(repository),
            getTeamsStatsPerGroupUseCase = GetTeamsStatsPerGroupUseCase(repository),
            checkIfGroupGamesCompletedUseCase = CheckIfGroupGamesCompletedUseCase(repository),
            calculateFinalStandingsUseCase = CalculateFinalStandingsUseCase(repository, playoffRepository)
        )
    }

    @Provides
    @Singleton
    fun provideFinalsRepository(database: QatarDatabase): PlayoffsRepository {
        return PlayoffsRepositoryImpl(qatarDatabase = database)
    }

    @Provides
    @Singleton
    fun providePlayoffUseCases(
        playoffRepository: PlayoffsRepository,
        teamRepository: TeamRepository,
        standingsRepository: StandingsRepository
    ): PlayoffsUseCases {
        return PlayoffsUseCases(
            enterKnockOutResultUseCase = EnterKnockOutResultUseCase(playoffRepository),
            getPlayoffByRoundKeyUseCase = GetPlayoffByRoundKeyUseCase(playoffRepository),
            getAllPlayoffsUseCase = GetAllPlayoffsUseCase(playoffRepository),
            setupPlayoffsUseCase = SetupPlayoffsUseCase(playoffRepository),
            updatePlayoffTeamUseCase = UpdatePlayoffTeamUseCase(playoffRepository),
            determinePlayoffWinnerUseCase = DeterminePlayoffWinnerUseCase(),
            updatePlayoffResultsUseCase = UpdatePlayoffResultsUseCase(playoffRepository, standingsRepository),
            checkIfPlayoffCompletedUseCase = CheckIfPlayoffCompletedUseCase(playoffRepository),
            getBestThreeTeamsUseCase = GetBestThreeTeamsUseCase(playoffRepository, teamRepository)
        )
    }

    @Provides
    @Singleton
    fun provideFixtureRepository(database: QatarDatabase): FixtureRepository {
        return FixtureRepositoryImpl(qatarDatabase = database)
    }

    @Provides
    @Singleton
    fun provideGroupDetailsUseCases(
        fixtureRepository: FixtureRepository,
        standingsRepository: StandingsRepository
    ): GroupDetailsUseCases {
        return GroupDetailsUseCases(
//            getMatchesByGroupUseCase = GetMatchesByGroupUseCase(repository),
            getFixtureByGroupUseCase = GetFixtureByGroupUseCase(fixtureRepository),
            updateFixtureUseCase = UpdateFixtureUseCase(fixtureRepository),
            calculatePointsUseCase = CalculatePointsUseCase(fixtureRepository, standingsRepository),
            calculateWeightedResultUseCase = CalculateWeightedResultUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideTeamRepository(database: QatarDatabase): TeamRepository {
        return TeamRepositoryImpl(qatarDatabase = database)
    }

    @Provides
    @Singleton
    fun provideTeamUseCase(
        teamRepository: TeamRepository
    ): GetTeamByIdUseCase {
        return GetTeamByIdUseCase(teamRepository)
    }

    @Provides
    @Singleton
    fun provideCountriesInfoRepository(api: CountriesInfoApi): CountriesInfoRepository {
        return CountriesInfoRepositoryImpl(countriesInfoApi = api)
    }

    @Provides
    @Singleton
    fun provideGetCountriesInfoUseCase(
        countriesInfoRepository: CountriesInfoRepository
    ): GetCountriesInfoUseCase {
        return GetCountriesInfoUseCase(countriesInfoRepository)
    }

    @Provides
    @Singleton
    fun provideDatabaseSetupRepository(database: QatarDatabase): DatabaseSetupRepository {
        return DatabaseSetupRepositoryImpl(qatarDatabase = database)
    }

    @Provides
    @Singleton
    fun provideDatabaseSetupUseCases(repository: DatabaseSetupRepository): DatabaseSetupUseCases {
        return DatabaseSetupUseCases(
            setGroupsFixtureUseCase = SetGroupsFixtureUseCase(repository),
            setStandingsUseCase = SetStandingsUseCase(repository),
            setGroupsUseCase = SetGroupsUseCase(repository),
            setTeamsUseCase = SetTeamsUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreOpsRepository(
        @ApplicationContext context: Context
    ): DataStoreOpsRepository {
        return DataStoreOpsRepositoryImpl(
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreUseCases(repository: DataStoreOpsRepository): DataStoreUseCases {
        return DataStoreUseCases(
            saveOnFixtureSetupUseCase = SaveOnFixtureSetupUseCase(repository),
            readOnFixtureSetupUseCase = ReadOnFixtureSetupUseCase(repository),
            saveOnStandingsSetupUseCase = SaveOnStandingsSetupUseCase(repository),
            readOnStandingsSetupUseCase = ReadOnStandingsSetupUseCase(repository),
            saveOnGroupsSetupUseCase = SaveOnGroupsSetupUseCase(repository),
            readOnGroupsSetupUseCase = ReadOnGroupsSetupUseCase(repository),
            saveOnTeamsSetupUseCase = SaveOnTeamsSetupUseCase(repository),
            readOnTeamsSetupUseCase = ReadOnTeamsSetupUseCase(repository)
        )
    }

}