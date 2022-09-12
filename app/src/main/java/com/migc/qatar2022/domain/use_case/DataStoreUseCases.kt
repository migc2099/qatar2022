package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.datastore.*

data class DataStoreUseCases(
    val saveOnFixtureSetupUseCase: SaveOnFixtureSetupUseCase,
    val readOnFixtureSetupUseCase: ReadOnFixtureSetupUseCase,
    val saveOnStandingsSetupUseCase: SaveOnStandingsSetupUseCase,
    val readOnStandingsSetupUseCase: ReadOnStandingsSetupUseCase,
    val saveOnGroupsSetupUseCase: SaveOnGroupsSetupUseCase,
    val readOnGroupsSetupUseCase: ReadOnGroupsSetupUseCase,
    val saveOnTeamsSetupUseCase: SaveOnTeamsSetupUseCase,
    val readOnTeamsSetupUseCase: ReadOnTeamsSetupUseCase
)
