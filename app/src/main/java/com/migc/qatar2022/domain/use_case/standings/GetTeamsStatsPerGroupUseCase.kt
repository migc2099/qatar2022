package com.migc.qatar2022.domain.use_case.standings

import com.migc.qatar2022.domain.model.Group
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.domain.repository.StandingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetTeamsStatsPerGroupUseCase(
    private val repository: StandingsRepository
) {

    operator fun invoke(): Flow<Map<Group, List<Team>>> {
        return flowOf(repository.getStatsPerGroup())
    }

}