package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.playoffs.*

data class PlayoffsUseCases(
    val enterKnockOutResultUseCase: EnterKnockOutResultUseCase,
    val getPlayoffByRoundKeyUseCase: GetPlayoffByRoundKeyUseCase,
    val getAllPlayoffsUseCase: GetAllPlayoffsUseCase,
    val setupPlayoffsUseCase: SetupPlayoffsUseCase,
    val updatePlayoffTeamUseCase: UpdatePlayoffTeamUseCase,
    val updatePlayoffResultsUseCase: UpdatePlayoffResultsUseCase
)
