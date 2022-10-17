package com.migc.qatar2022.presentation.screens.teams_map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.domain.model.Predictions
import com.migc.qatar2022.domain.use_case.FirebaseUseCases
import com.migc.qatar2022.domain.use_case.NetworkUseCases
import com.migc.qatar2022.domain.use_case.TeamsMapUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsMapViewModel @Inject constructor(
    private val teamsMapUseCases: TeamsMapUseCases,
    private val networkUseCases: NetworkUseCases,
    private val firebaseUseCases: FirebaseUseCases
) : ViewModel() {

    private val _data: MutableStateFlow<List<CountryInfo>> = MutableStateFlow(emptyList())
    val data = _data.asStateFlow()

    private val _currentCountry: MutableStateFlow<CountryInfo> = MutableStateFlow(CountryInfo())
    val countryInfo = _currentCountry.asStateFlow()

    private val _predictions: MutableStateFlow<PredictionsState> = MutableStateFlow(PredictionsState())
    val predictions = _predictions.asStateFlow()

    private val _topPredictions: MutableStateFlow<List<Predictions>> = MutableStateFlow(emptyList())
    val topPredictions = _topPredictions.asStateFlow()

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
            teamsMapUseCases.getTopPredictionsUseCase().collect { result ->
                Log.d("TeamsMapViewModel", "predictions: $result")
                when (result) {
                    is Resource.Success -> {
                        if (result.data != null) {
                            _topPredictions.value = result.data
                        }
                    }
                    is Resource.Error -> {
                        Log.e("init", "topPredictions error: ${result.message}")
                    }
                    else -> {}
                }

            }
        }
    }

    fun onEvent(teamsMapUiEvent: TeamsMapUiEvent) {
        when (teamsMapUiEvent) {
            is TeamsMapUiEvent.OnSeePredictionsClicked -> {
                _predictions.value = PredictionsState(isLoading = true)
                val connectionAvailable = networkUseCases.checkIfInternetAvailableUseCase()
                if (connectionAvailable) {
                    val user = firebaseUseCases.getFirebaseAuthUseCase()
                    if (user != null) {
                        if (_predictions.value.data == null) {
                            viewModelScope.launch(Dispatchers.IO) {
                                val result = teamsMapUseCases.getPredictionsUseCase(teamId = teamsMapUiEvent.teamId)
                                when (result) {
                                    is Resource.Loading -> {
                                        _predictions.value = PredictionsState(
                                            isLoading = true
                                        )
                                    }
                                    is Resource.Success -> {
                                        _predictions.value = PredictionsState(
                                            isLoading = false,
                                            data = result.data
                                        )
                                    }
                                    is Resource.Error -> {
                                        _predictions.value = PredictionsState(
                                            error = result.message.toString()
                                        )
                                    }
                                }

                            }
                        }
                    } else {
                        _predictions.value = PredictionsState(
                            isLoading = false,
                            error = Constants.SIGN_IN_REQUIRED_MESSAGE
                        )
                    }
                } else {
                    _predictions.value = PredictionsState(
                        isLoading = false,
                        error = Constants.CONNECTION_EXCEPTION_ERROR_MESSAGE
                    )
                }
            }
            is TeamsMapUiEvent.OnCountryFlagClicked -> {
                if (_currentCountry.value.teamId != teamsMapUiEvent.countryInfo.teamId) {
                    _currentCountry.value = teamsMapUiEvent.countryInfo
                    Log.d("onEvent", "_predictions.value = PredictionsState()")
                    _predictions.value = PredictionsState()
                }
            }
        }
    }

}