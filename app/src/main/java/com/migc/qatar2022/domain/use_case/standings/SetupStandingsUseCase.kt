package com.migc.qatar2022.domain.use_case.standings

import com.migc.qatar2022.domain.repository.StandingsRepository
import javax.inject.Inject

class SetupStandingsUseCase (
    private val standingsRepository: StandingsRepository
) {

    suspend operator fun invoke() {
        standingsRepository.insertTeams(standingsRepository.setUpTeams())
    }

}