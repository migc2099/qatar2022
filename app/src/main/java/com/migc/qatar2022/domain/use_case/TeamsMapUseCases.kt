package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.teams_map.GetCountriesInfoUseCase
import com.migc.qatar2022.domain.use_case.teams_map.GetPredictionsUseCase
import com.migc.qatar2022.domain.use_case.teams_map.GetTopPredictionsUseCase

data class TeamsMapUseCases(
    val getCountriesInfoUseCase: GetCountriesInfoUseCase,
    val getPredictionsUseCase: GetPredictionsUseCase,
    val getTopPredictionsUseCase: GetTopPredictionsUseCase
)