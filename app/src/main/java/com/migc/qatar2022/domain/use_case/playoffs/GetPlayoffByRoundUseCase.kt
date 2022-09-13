package com.migc.qatar2022.domain.use_case.playoffs

import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.repository.PlayoffsRepository

class GetPlayoffByRoundUseCase(
    private val playoffsRepository: PlayoffsRepository
) {

    suspend operator fun invoke(roundKey: Int): Playoff {
        return playoffsRepository.getPlayoffByRoundKey(roundKey)
    }

}