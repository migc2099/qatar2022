package com.migc.qatar2022.domain.use_case.teams_map

import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.model.Predictions
import com.migc.qatar2022.domain.repository.CountriesInfoRepository
import kotlinx.coroutines.flow.Flow

class GetTopPredictionsUseCase(
    private val repository: CountriesInfoRepository
) {

    operator fun invoke(): Flow<Resource<List<Predictions>>> {
        return repository.getTopPredictions()
    }

}