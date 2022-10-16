package com.migc.qatar2022.presentation.screens.teams_map

import com.migc.qatar2022.domain.model.CountryInfo

sealed class TeamsMapUiEvent {

    data class OnCountryFlagClicked(val countryInfo: CountryInfo) : TeamsMapUiEvent()
    data class OnSeePredictionsClicked(val teamId: String) : TeamsMapUiEvent()

}
