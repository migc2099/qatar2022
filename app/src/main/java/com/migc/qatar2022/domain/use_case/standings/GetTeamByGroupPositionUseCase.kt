package com.migc.qatar2022.domain.use_case.standings

import com.migc.qatar2022.domain.model.TeamStat
import com.migc.qatar2022.domain.repository.StandingsRepository
import javax.inject.Inject

class GetTeamByGroupPositionUseCase (
    private val standingsRepository: StandingsRepository
) {

    suspend operator fun invoke(group: String, position: Int): TeamStat {
        return standingsRepository.getTeamByGroupPosition(group, position)
    }

}