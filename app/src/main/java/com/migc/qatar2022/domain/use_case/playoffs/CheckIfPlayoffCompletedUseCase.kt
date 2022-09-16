package com.migc.qatar2022.domain.use_case.playoffs

import com.migc.qatar2022.domain.repository.PlayoffsRepository

class CheckIfPlayoffCompletedUseCase(
    private val repository: PlayoffsRepository
) {
    suspend operator fun invoke(): Boolean {
        val thirdPlaceGame = repository.getPlayoffByRoundKey(63)
        val finalGame = repository.getPlayoffByRoundKey(64)
        return thirdPlaceGame.winnerTeam.isNotEmpty() && thirdPlaceGame.loserTeam.isNotEmpty() &&
                finalGame.winnerTeam.isNotEmpty() && finalGame.loserTeam.isNotEmpty()
    }
}