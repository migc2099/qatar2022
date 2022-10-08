package com.migc.qatar2022.domain.use_case.playoffs

import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.domain.repository.PlayoffsRepository

class UploadWinnerCountersUseCase(
    private val playoffsRepository: PlayoffsRepository
) {

    suspend operator fun invoke(teams: List<Team>) {
        playoffsRepository.uploadWinners(teams)
    }
}