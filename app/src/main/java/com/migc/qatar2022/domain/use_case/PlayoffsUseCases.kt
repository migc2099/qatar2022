package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.playoffs.EnterKnockOutResultUseCase
import com.migc.qatar2022.domain.use_case.playoffs.GetPlayoffByRoundUseCase
import com.migc.qatar2022.domain.use_case.playoffs.SetupPlayoffsUseCase
import com.migc.qatar2022.domain.use_case.playoffs.UpdatePlayoffUseCase

data class PlayoffsUseCases(
    val enterKnockOutResultUseCase: EnterKnockOutResultUseCase,
    val getPlayoffByRoundUseCase: GetPlayoffByRoundUseCase,
    val setupPlayoffsUseCase: SetupPlayoffsUseCase,
    val updatePlayoffUseCase: UpdatePlayoffUseCase
)
