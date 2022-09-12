package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.database_setup.SetGroupsFixtureUseCase
import com.migc.qatar2022.domain.use_case.database_setup.SetGroupsUseCase
import com.migc.qatar2022.domain.use_case.database_setup.SetStandingsUseCase
import com.migc.qatar2022.domain.use_case.database_setup.SetTeamsUseCase

data class DatabaseSetupUseCases(
    val setGroupsFixtureUseCase: SetGroupsFixtureUseCase,
    val setStandingsUseCase: SetStandingsUseCase,
    val setGroupsUseCase: SetGroupsUseCase,
    val setTeamsUseCase: SetTeamsUseCase
)