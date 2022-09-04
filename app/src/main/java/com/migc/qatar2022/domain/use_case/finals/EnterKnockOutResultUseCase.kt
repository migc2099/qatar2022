package com.migc.qatar2022.domain.use_case.finals

import com.migc.qatar2022.domain.model.KnockOutMatch
import com.migc.qatar2022.domain.repository.FinalsRepository

class EnterKnockOutResultUseCase(
    private val finalsRepository: FinalsRepository
) {

    suspend operator fun invoke(knockOutMatchResult: List<KnockOutMatch>) {
        finalsRepository.updateFinals(knockOutMatchResult)
    }

}