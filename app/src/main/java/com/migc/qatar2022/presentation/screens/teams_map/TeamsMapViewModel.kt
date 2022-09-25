package com.migc.qatar2022.presentation.screens.teams_map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.domain.use_case.teams_map.GetCountriesInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsMapViewModel @Inject constructor(
    private val getCountriesInfoUseCase: GetCountriesInfoUseCase
) : ViewModel() {

    private val _data: MutableStateFlow<List<CountryInfo>> = MutableStateFlow(emptyList())
    val data = _data.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCountriesInfoUseCase()
                .onSuccess {
                    _data.value = it
                }
                .onFailure {
                    Log.e("TeamsMapViewModel", it.message.toString())
                    _data.value = emptyList()
                }
        }

    }

}