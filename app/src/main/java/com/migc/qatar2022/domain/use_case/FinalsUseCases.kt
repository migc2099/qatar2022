package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.finals.EnterKnockOutResultUseCase
import com.migc.qatar2022.domain.use_case.finals.GetMatchByRoundUseCase
import com.migc.qatar2022.domain.use_case.finals.SetupFinalsUseCase

data class FinalsUseCases(
    val enterKnockOutResultUseCase: EnterKnockOutResultUseCase,
    val getMatchByRoundUseCase: GetMatchByRoundUseCase,
    val setupFinalsUseCase: SetupFinalsUseCase
)
