package com.migc.qatar2022.domain.use_case.playoffs

import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.repository.PlayoffsRepository

class GetPlayoffByRoundKeyUseCase(
    private val repository: PlayoffsRepository
) {

    suspend operator fun invoke(roundKey: Int): Playoff {
        return repository.getPlayoffByRoundKey(roundKey)
    }

}