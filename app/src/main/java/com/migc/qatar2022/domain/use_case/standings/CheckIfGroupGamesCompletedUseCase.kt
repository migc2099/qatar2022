package com.migc.qatar2022.domain.use_case.standings

import com.migc.qatar2022.domain.repository.StandingsRepository

class CheckIfGroupGamesCompletedUseCase(
    private val repository: StandingsRepository
) {

    operator fun invoke(groupKey: String): Boolean {
        return repository.checkIfGroupGamesCompleted(groupKey)
    }

}