package com.migc.qatar2022.domain.use_case.finals

import com.migc.qatar2022.domain.model.KnockOutMatch
import com.migc.qatar2022.domain.repository.FinalsRepository

class GetMatchByRoundUseCase(
    private val finalsRepository: FinalsRepository
) {

    suspend operator fun invoke(roundKey: Int): KnockOutMatch {
        return finalsRepository.getFinalByRoundKey(roundKey)
    }

}