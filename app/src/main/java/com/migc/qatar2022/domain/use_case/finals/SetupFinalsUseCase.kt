package com.migc.qatar2022.domain.use_case.finals

import com.migc.qatar2022.domain.repository.FinalsRepository

class SetupFinalsUseCase(
    private val finalsRepository: FinalsRepository
) {
    suspend operator fun invoke() {
        finalsRepository.resetFinals()
    }
}