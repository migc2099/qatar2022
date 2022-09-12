package com.migc.qatar2022.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.qatar2022.domain.model.Group
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.domain.use_case.StandingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val usesCases: StandingsUseCases
) : ViewModel() {

    var listPosition = 0
    var listOffSet = 0

    private var _statsPerGroup: MutableStateFlow<Map<Group, List<Team>>> = MutableStateFlow(emptyMap())
    val statsPerGroup = _statsPerGroup.asStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnStart -> {
                viewModelScope.launch(Dispatchers.IO) {
                    usesCases.getTeamsStatsPerGroupUseCase()
                        .collect { statsMap ->
                            _statsPerGroup.value = statsMap
                        }

                }
            }
            is HomeUiEvent.OnNavigateToGroupDetails -> {
                listPosition = event.listIndex
                listOffSet = event.scrollOffSet
            }
        }
    }
}