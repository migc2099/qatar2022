package com.migc.qatar2022.domain.use_case.playoffs

import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.repository.PlayoffsRepository

class GetAllPlayoffsUseCase(
    private val playoffsRepository: PlayoffsRepository
) {

    suspend operator fun invoke(): List<Playoff> {
        return playoffsRepository.getAllPlayoffs()
    }

}