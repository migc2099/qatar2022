package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.standings.*

data class StandingsUseCases(
    val setupStandingsUseCase: SetupStandingsUseCase,
    val getTeamsByGroupUseCase: GetTeamsByGroupUseCase,
    val getTeamByGroupPositionUseCase: GetTeamByGroupPositionUseCase,
    val updateStandingsUseCase: UpdateStandingsUseCase,
    val getTeamsStatsPerGroupUseCase: GetTeamsStatsPerGroupUseCase
)
