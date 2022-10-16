package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.teams_map.GetCountriesInfoUseCase
import com.migc.qatar2022.domain.use_case.teams_map.GetPredictionsUseCase

data class TeamsMapUseCases(
    val getCountriesInfoUseCase: GetCountriesInfoUseCase,
    val getPredictionsUseCase: GetPredictionsUseCase
)