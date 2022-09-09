package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.database_setup.SetGroupsFixtureUseCase
import com.migc.qatar2022.domain.use_case.database_setup.SetStandingsUseCase

data class DatabaseSetupUseCases(
    val setGroupsFixtureUseCase: SetGroupsFixtureUseCase,
    val setStandingsUseCase: SetStandingsUseCase
)