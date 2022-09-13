package com.migc.qatar2022.domain.use_case.playoffs

import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.repository.PlayoffsRepository

class EnterKnockOutResultUseCase(
    private val playoffsRepository: PlayoffsRepository
) {

    suspend operator fun invoke(playoffResult: List<Playoff>) {
        playoffsRepository.updatePlayoffs(playoffResult)
    }

}