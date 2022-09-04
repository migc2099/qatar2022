package com.migc.qatar2022.domain.use_case.standings

import com.migc.qatar2022.domain.model.TeamStat
import com.migc.qatar2022.domain.repository.StandingsRepository
import javax.inject.Inject

class GetTeamsByGroupUseCase (
    private val standingsRepository: StandingsRepository
) {

    suspend operator fun invoke(group: String): List<TeamStat> {
        return standingsRepository.getTeamsByGroup(group)
    }

}