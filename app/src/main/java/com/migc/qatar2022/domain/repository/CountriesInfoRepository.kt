package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.data.remote.dto.PredictionsDto
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.domain.model.Predictions
import kotlinx.coroutines.flow.Flow

interface CountriesInfoRepository {

    suspend fun getData(): Result<List<CountryInfo>>
    suspend fun getPredictions(teamId: String): Resource<PredictionsDto>
    fun getTopPredictions(): Flow<Resource<List<Predictions>>>

}