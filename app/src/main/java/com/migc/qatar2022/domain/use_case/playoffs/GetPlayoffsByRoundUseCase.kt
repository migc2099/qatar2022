package com.migc.qatar2022.domain.use_case.playoffs

import com.migc.qatar2022.common.Constants.FINALS_STAGE_NUMBER
import com.migc.qatar2022.common.Constants.ROUND_OF_16_STAGE_NUMBER
import com.migc.qatar2022.common.Constants.ROUND_OF_8_STAGE_NUMBER
import com.migc.qatar2022.common.Constants.SEMIFINALS_STAGE_NUMBER
import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.repository.PlayoffsRepository

class GetPlayoffsByRoundUseCase(
    private val playoffsRepository: PlayoffsRepository
) {

    suspend operator fun invoke(stageNumber: Int): List<Playoff> {
        return when (stageNumber) {
            ROUND_OF_16_STAGE_NUMBER -> playoffsRepository.getPlayoffsByRound(startRound = 49, endRound = 56)
            ROUND_OF_8_STAGE_NUMBER -> playoffsRepository.getPlayoffsByRound(startRound = 57, endRound = 60)
            SEMIFINALS_STAGE_NUMBER -> playoffsRepository.getPlayoffsByRound(startRound = 61, endRound = 62)
            FINALS_STAGE_NUMBER -> playoffsRepository.getPlayoffsByRound(startRound = 63, endRound = 64)
            else -> {
                emptyList()
            }
        }
    }

}