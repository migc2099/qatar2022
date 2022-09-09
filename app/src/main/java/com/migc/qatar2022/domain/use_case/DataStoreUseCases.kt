package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.datastore.ReadOnFixtureSetupUseCase
import com.migc.qatar2022.domain.use_case.datastore.SaveOnFixtureSetupUseCase

data class DataStoreUseCases(
    val saveOnFixtureSetupUseCase: SaveOnFixtureSetupUseCase,
    val readOnFixtureSetupUseCase: ReadOnFixtureSetupUseCase
)
