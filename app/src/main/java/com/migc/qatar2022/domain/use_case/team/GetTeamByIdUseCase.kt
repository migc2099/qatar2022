package com.migc.qatar2022.domain.use_case.team

import com.migc.qatar2022.data.local.mapper.toTeam
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.domain.repository.TeamRepository

class GetTeamByIdUseCase(
    private val repository: TeamRepository
) {

    suspend operator fun invoke(id: String): Team {
        return repository.getTeamById(id).toTeam()
    }

}