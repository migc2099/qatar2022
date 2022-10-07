package com.migc.qatar2022.presentation.screens.teams_map

import com.migc.qatar2022.domain.model.BettingOdds

data class OddsDetailsState(
    val isLoading: Boolean = false,
    val bettingOdds: BettingOdds? = null,
    val error: String = ""
)