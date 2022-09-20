package com.migc.qatar2022.presentation.screens.standings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.qatar2022.domain.model.TeamStat
import com.migc.qatar2022.domain.use_case.StandingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
    private val useCases: StandingsUseCases
) : ViewModel() {

    private var _teamStats: MutableStateFlow<List<TeamStat>> = MutableStateFlow(emptyList())
    val teamStats = _teamStats.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _teamStats.value = useCases.calculateFinalStandingsUseCase()
        }
    }

}