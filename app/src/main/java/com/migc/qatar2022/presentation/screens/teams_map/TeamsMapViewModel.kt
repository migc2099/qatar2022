package com.migc.qatar2022.presentation.screens.teams_map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.domain.use_case.TeamsMapUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsMapViewModel @Inject constructor(
    private val teamsMapUseCases: TeamsMapUseCases
) : ViewModel() {

    private val _data: MutableStateFlow<List<CountryInfo>> = MutableStateFlow(emptyList())
    val data = _data.asStateFlow()

    private val _currentCountry: MutableStateFlow<CountryInfo> = MutableStateFlow(CountryInfo())
    val countryInfo = _currentCountry.asStateFlow()

    private val _odds: MutableStateFlow<OddsDetailsState> = MutableStateFlow(OddsDetailsState())
    val odds = _odds.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            teamsMapUseCases.getCountriesInfoUseCase()
                .onSuccess {
                    _data.value = it
                }
                .onFailure {
                    Log.e("TeamsMapViewModel", it.message.toString())
                    _data.value = emptyList()
                }
        }
    }

    fun onEvent(teamsMapUiEvent: TeamsMapUiEvent) {
        when (teamsMapUiEvent) {
            is TeamsMapUiEvent.OnSeeOddsClicked -> {
                if (_odds.value.bettingOdds == null){
                    _odds.value = OddsDetailsState(isLoading = true)
                    viewModelScope.launch(Dispatchers.IO) {
                        val odds = teamsMapUseCases.getTeamOddsUseCase(teamId = teamsMapUiEvent.teamId)
                        _odds.value = OddsDetailsState(isLoading = false, bettingOdds = odds)
                    }
                }
            }
            is TeamsMapUiEvent.OnCountryFlagClicked -> {
                if (_currentCountry.value.teamId != teamsMapUiEvent.countryInfo.teamId) {
                    _currentCountry.value = teamsMapUiEvent.countryInfo
                    Log.d("onEvent", "_odds.value = OddsDetailsState()")
                    _odds.value = OddsDetailsState()
                }
            }
        }
    }

}