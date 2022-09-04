package com.migc.qatar2022.domain.use_case.standings

import com.migc.qatar2022.domain.model.TeamStat
import com.migc.qatar2022.domain.repository.StandingsRepository
import javax.inject.Inject

class UpdateStandingsUseCase (
    private val standingsRepository: StandingsRepository
) {

    suspend operator fun invoke(standings: List<TeamStat>) {
        standingsRepository.insertTeams(standings)
    }

}