package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.datastore.ReadOnFixtureSetupUseCase
import com.migc.qatar2022.domain.use_case.datastore.ReadOnStandingsSetupUseCase
import com.migc.qatar2022.domain.use_case.datastore.SaveOnFixtureSetupUseCase
import com.migc.qatar2022.domain.use_case.datastore.SaveOnStandingsSetupUseCase

data class DataStoreUseCases(
    val saveOnFixtureSetupUseCase: SaveOnFixtureSetupUseCase,
    val readOnFixtureSetupUseCase: ReadOnFixtureSetupUseCase,
    val saveOnStandingsSetupUseCase: SaveOnStandingsSetupUseCase,
    val readOnStandingsSetupUseCase: ReadOnStandingsSetupUseCase
)
