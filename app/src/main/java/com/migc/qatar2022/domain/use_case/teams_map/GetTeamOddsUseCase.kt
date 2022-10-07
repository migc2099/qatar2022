package com.migc.qatar2022.domain.use_case.teams_map

import com.migc.qatar2022.data.remote.dto.toBettingOdds
import com.migc.qatar2022.domain.model.BettingOdds
import com.migc.qatar2022.domain.repository.CountriesInfoRepository

class GetTeamOddsUseCase(
    private val countriesInfoRepository: CountriesInfoRepository
) {
    suspend operator fun invoke(teamId: String): BettingOdds {
        return countriesInfoRepository.getOdds(teamId).toBettingOdds()
    }
}