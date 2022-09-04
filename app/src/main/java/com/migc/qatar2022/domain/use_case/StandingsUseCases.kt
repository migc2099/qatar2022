package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.standings.GetTeamByGroupPositionUseCase
import com.migc.qatar2022.domain.use_case.standings.GetTeamsByGroupUseCase
import com.migc.qatar2022.domain.use_case.standings.SetupStandingsUseCase
import com.migc.qatar2022.domain.use_case.standings.UpdateStandingsUseCase

data class StandingsUseCases(
    val setupStandingsUseCase: SetupStandingsUseCase,
    val getTeamsByGroupUseCase: GetTeamsByGroupUseCase,
    val getTeamByGroupPositionUseCase: GetTeamByGroupPositionUseCase,
    val updateStandingsUseCase: UpdateStandingsUseCase
)
