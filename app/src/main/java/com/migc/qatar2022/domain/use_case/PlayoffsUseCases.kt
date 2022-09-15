package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.playoffs.EnterKnockOutResultUseCase
import com.migc.qatar2022.domain.use_case.playoffs.GetPlayoffsByRoundUseCase
import com.migc.qatar2022.domain.use_case.playoffs.SetupPlayoffsUseCase
import com.migc.qatar2022.domain.use_case.playoffs.UpdatePlayoffUseCase

data class PlayoffsUseCases(
    val enterKnockOutResultUseCase: EnterKnockOutResultUseCase,
    val getPlayoffsByRoundUseCase: GetPlayoffsByRoundUseCase,
    val setupPlayoffsUseCase: SetupPlayoffsUseCase,
    val updatePlayoffUseCase: UpdatePlayoffUseCase
)
