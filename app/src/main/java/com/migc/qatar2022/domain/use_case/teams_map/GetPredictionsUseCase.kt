package com.migc.qatar2022.domain.use_case.teams_map

import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.data.remote.dto.toPredictions
import com.migc.qatar2022.domain.model.Predictions
import com.migc.qatar2022.domain.repository.CountriesInfoRepository

class GetPredictionsUseCase(
    private val countriesInfoRepository: CountriesInfoRepository
) {
    suspend operator fun invoke(teamId: String): Resource<Predictions> {
        val result = countriesInfoRepository.getPredictions(teamId)
        return when (result) {
            is Resource.Success -> {
                if (result.data != null) {
                    Resource.Success(result.data.toPredictions())
                } else {
                    Resource.Success(Predictions())
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