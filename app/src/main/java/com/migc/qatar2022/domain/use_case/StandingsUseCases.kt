package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.standings.*

data class StandingsUseCases(
    val setupStandingsUseCase: SetupStandingsUseCase,
    val getTeamsByGroupUseCase: GetTeamsByGroupUseCase,
    val updateStandingsUseCase: UpdateStandingsUseCase,
    val getTeamsStatsPerGroupUseCase: GetTeamsStatsPerGroupUseCase,
    val checkIfGroupGamesCompletedUseCase: CheckIfGroupGamesCompletedUseCase,
    val calculateFinalStandingsUseCase: CalculateFinalStandingsUseCase
)
