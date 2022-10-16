package com.migc.qatar2022.presentation.screens.teams_map

import com.migc.qatar2022.domain.model.Predictions

data class PredictionsState(
    val isLoading: Boolean = false,
    val data: Predictions? = null,
    val error: String = ""
)