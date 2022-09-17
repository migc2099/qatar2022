package com.migc.qatar2022.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.qatar2022.common.Constants.GROUP_A_KEY
import com.migc.qatar2022.common.Constants.GROUP_B_KEY
import com.migc.qatar2022.common.Constants.GROUP_C_KEY
import com.migc.qatar2022.common.Constants.GROUP_D_KEY
import com.migc.qatar2022.common.Constants.GROUP_E_KEY
import com.migc.qatar2022.common.Constants.GROUP_F_KEY
import com.migc.qatar2022.common.Constants.GROUP_G_KEY
import com.migc.qatar2022.common.Constants.GROUP_H_KEY
import com.migc.qatar2022.domain.model.Group
import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.domain.use_case.DatabaseSetupUseCases
import com.migc.qatar2022.domain.use_case.PlayoffsUseCases
import com.migc.qatar2022.domain.use_case.StandingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val standingsUsesCases: StandingsUseCases,
    private val playoffsUseCases: PlayoffsUseCases,
    private val databaseSetupUseCases: DatabaseSetupUseCases
) : ViewModel() {

    var listPosition = 0
    var listOffSet = 0

    private var _statsPerGroup: MutableStateFlow<Map<Group, List<Team>>> = MutableStateFlow(emptyMap())
    val statsPerGroup = _statsPerGroup.asStateFlow()

    private var _playoffs: MutableStateFlow<List<Playoff>> = MutableStateFlow(emptyList())
    val playoffs = _playoffs.asStateFlow()

    private var _selectedPlayoff: MutableStateFlow<Playoff> = MutableStateFlow(Playoff(0))
    val selectedPlayoff = _selectedPlayoff.asStateFlow()

    private var _playoffCompletedState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val playoffCompletedState = _playoffCompletedState.asStateFlow()

    private var _bestTeams: MutableStateFlow<Array<Team>> = MutableStateFlow(emptyArray())
    val bestTeams = _bestTeams.asStateFlow()


    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnStart -> {
                viewModelScope.launch(Dispatchers.IO) {
                    standingsUsesCases.getTeamsStatsPerGroupUseCase()
                        .collect { statsMap ->
                            _statsPerGroup.value = statsMap
                        }
                    fillRoundOf16Games()
                }
            }
            is HomeUiEvent.OnNavigateToGroupDetails -> {
                listPosition = event.listIndex
                listOffSet = event.scrollOffSet
            }
            is HomeUiEvent.OnPlayoffDialogClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _selectedPlayoff.value = playoffsUseCases.getPlayoffByRoundKeyUseCase(event.roundKey)
                    _playoffCompletedState.value = false
                }
            }
            is HomeUiEvent.OnPlayoffDialogCompleted -> {
                viewModelScope.launch(Dispatchers.IO) {
                    Log.d("LOG", "updatePlayoffResultsUseCase: ${event.playoff}")
                    val completedPlayoff = playoffsUseCases.determinePlayoffWinnerUseCase(event.playoff)
                    playoffsUseCases.updatePlayoffResultsUseCase(completedPlayoff)
                    playoffsUseCases.updatePlayoffResultsUseCase(
                        formerWinnerTeam = completedPlayoff.loserTeam,
                        newWinnerTeam = completedPlayoff.winnerTeam,
                        currentRoundKey = completedPlayoff.roundKey
                    )
                    refreshPlayoffsGrid()
                }
            }
            is HomeUiEvent.OnResetPlayoffsClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    resetPlayoffs()
                    fillRoundOf16Games()
                }
            }
        }
    }

    private suspend fun fillRoundOf16Games(){
        val completedGroupsMap = mutableStateMapOf(
            GROUP_A_KEY to false,
            GROUP_B_KEY to false,
            GROUP_C_KEY to false,
            GROUP_D_KEY to false,
            GROUP_E_KEY to false,
            GROUP_F_KEY to false,
            GROUP_G_KEY to false,
            GROUP_H_KEY to false
        )
        var groupsCompleted = 0
        completedGroupsMap[GROUP_A_KEY] = standingsUsesCases.checkIfGroupGamesCompletedUseCase(GROUP_A_KEY)
        completedGroupsMap[GROUP_B_KEY] = standingsUsesCases.checkIfGroupGamesCompletedUseCase(GROUP_B_KEY)
        completedGroupsMap[GROUP_C_KEY] = standingsUsesCases.checkIfGroupGamesCompletedUseCase(GROUP_C_KEY)
        completedGroupsMap[GROUP_D_KEY] = standingsUsesCases.checkIfGroupGamesCompletedUseCase(GROUP_D_KEY)
        completedGroupsMap[GROUP_E_KEY] = standingsUsesCases.checkIfGroupGamesCompletedUseCase(GROUP_E_KEY)
        completedGroupsMap[GROUP_F_KEY] = standingsUsesCases.checkIfGroupGamesCompletedUseCase(GROUP_F_KEY)
        completedGroupsMap[GROUP_G_KEY] = standingsUsesCases.checkIfGroupGamesCompletedUseCase(GROUP_G_KEY)
        completedGroupsMap[GROUP_H_KEY] = standingsUsesCases.checkIfGroupGamesCompletedUseCase(GROUP_H_KEY)
        delay(200)
        completedGroupsMap.forEach { mapItem ->
            Log.d("completedGroupsMap.forEach", mapItem.value.toString())
            if (mapItem.value) {
                val group = _statsPerGroup.value[Group(mapItem.key, "Group ${mapItem.key}")]
                if (group != null) {
                    val firstTeam = group[0]
                    val secondTeam = group[1]
                    Log.d("HomeViewModel", "firstTeam: $firstTeam")
                    Log.d("HomeViewModel", "secondTeam: $secondTeam")
                    playoffsUseCases.updatePlayoffTeamUseCase(firstTeam.teamId, mapItem.key, 1)
                    playoffsUseCases.updatePlayoffTeamUseCase(secondTeam.teamId, mapItem.key, 2)
                    groupsCompleted++
                }
            }
        }
        Log.d("onEvent", "before groupsCompleted == 0")
        if (groupsCompleted == 0) {
            resetPlayoffs()
        } else {
            refreshPlayoffsGrid()
        }
    }

    private suspend fun resetPlayoffs() {
            playoffsUseCases.setupPlayoffsUseCase()
    }

    private suspend fun refreshPlayoffsGrid() {
            _selectedPlayoff.value = Playoff(0)
            _playoffs.value = playoffsUseCases.getAllPlayoffsUseCase()
            refreshPlayoffCompletionStatus()
            getBestTeams()
    }

    private fun refreshPlayoffCompletionStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            _playoffCompletedState.value = playoffsUseCases.checkIfPlayoffCompletedUseCase()
        }
    }

    private fun getBestTeams() {
        viewModelScope.launch(Dispatchers.IO) {
            _bestTeams.value = playoffsUseCases.getBestThreeTeamsUseCase()
        }
    }

    fun resetTournament() {
        _playoffs.value = emptyList()
        _statsPerGroup.value = emptyMap()
        _playoffCompletedState.value = false
        viewModelScope.launch(Dispatchers.IO) {
            resetPlayoffs()
            databaseSetupUseCases.setStandingsUseCase()
            databaseSetupUseCases.setGroupsFixtureUseCase()
            standingsUsesCases.getTeamsStatsPerGroupUseCase()
                .collect { statsMap ->
                    _statsPerGroup.value = statsMap
                }
            refreshPlayoffsGrid()
        }
    }

}