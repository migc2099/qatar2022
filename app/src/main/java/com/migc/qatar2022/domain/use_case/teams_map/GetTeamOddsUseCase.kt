package com.migc.qatar2022.domain.use_case.teams_map

import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.data.remote.dto.toBettingOdds
import com.migc.qatar2022.domain.model.BettingOdds
import com.migc.qatar2022.domain.repository.CountriesInfoRepository

class GetTeamOddsUseCase(
    private val countriesInfoRepository: CountriesInfoRepository
) {
    suspend operator fun invoke(teamId: String): Resource<BettingOdds> {
        val result = countriesInfoRepository.getOdds(teamId)
        return when (result) {
            is Resource.Success -> {
                if (result.data != null) {
                    Resource.Success(result.data.toBettingOdds())
                } else {
                    Resource.Success(BettingOdds(wc = 0))
                }
            }
            is Resource.Error -> {
                Resource.Error(message = result.message.toString())
            }
            else -> {
                Resource.Loading()
            }
        }
    }
}