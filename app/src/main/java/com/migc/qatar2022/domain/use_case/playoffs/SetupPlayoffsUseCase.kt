package com.migc.qatar2022.domain.use_case.playoffs

import com.migc.qatar2022.domain.repository.PlayoffsRepository

class SetupPlayoffsUseCase(
    private val playoffsRepository: PlayoffsRepository
) {
    suspend operator fun invoke() {
        playoffsRepository.resetPlayoffs()
    }
}