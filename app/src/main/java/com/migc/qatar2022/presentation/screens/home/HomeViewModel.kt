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
import com.migc.qatar2022.common.Constants.CONNECTION_EXCEPTION_ERROR_MESSAGE
import com.migc.qatar2022.common.Constants.UNEXPECTED_EXCEPTION_ERROR_MESSAGE
import com.migc.qatar2022.common.Constants.UPLOAD_COMPLETED_MESSAGE
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.model.Group
import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.domain.use_case.*
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
    private val databaseSetupUseCases: DatabaseSetupUseCases,
    private val firebaseUseCases: FirebaseUseCases,
    private val dataStoreUseCases: DataStoreUseCases,
    private val networkUseCases: NetworkUseCases
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

    private val _userState: MutableStateFlow<UserState> = MutableStateFlow(UserState())
    val userState = _userState.asStateFlow()

    private val _tournamentActionState: MutableStateFlow<TournamentActionType> = MutableStateFlow(TournamentActionType.Undefined)
    val tournamentActionState = _tournamentActionState.asStateFlow()

    private val _uploadState = MutableSharedFlow<UploadWinnersState>()
    val uploadState = _uploadState.asSharedFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnStart -> {
                _userState.value = UserState(firebaseUseCases.getFirebaseAuthUseCase())
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
                if (_playoffs.value.isNotEmpty()) {
                    viewModelScope.launch(Dispatchers.IO) {
                        resetPlayoffs()
                        fillRoundOf16Games()
                    }
                }
            }
            is HomeUiEvent.OnPlayoffDialogClicked -> {
                _playoffCompletedState.value = false
                viewModelScope.launch(Dispatchers.IO) {
                    _selectedPlayoff.value = playoffsUseCases.getPlayoffByRoundKeyUseCase(event.roundKey)
                }
            }
            is HomeUiEvent.OnPlayoffDialogDismissed -> {
                viewModelScope.launch(Dispatchers.IO) {
                    refreshPlayoffsGrid()
                }
            }
            is HomeUiEvent.OnPlayoffDialogCompleted -> {
                _tournamentActionState.value = TournamentActionType.PlayoffPlayed
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
            is HomeUiEvent.OnShowStandingsClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getFinalStandings()
                }
            }
            is HomeUiEvent.OnUploadWinnersClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uploadState.emit(UploadWinnersState(operationState = OperationState.Loading))
                    if (_userState.value.user != null && _tournamentActionState.value == TournamentActionType.FinalsFinished) {
                        val isThereInternet = networkUseCases.checkIfInternetAvailableUseCase()
                        _uploadState.emit(UploadWinnersState(operationState = OperationState.InternetChecked))
                        delay(1000)
                        if (isThereInternet) {
                            val result = playoffsUseCases.uploadWinnerCountersUseCase(teams = event.winners)
                            when (result) {
                                is Resource.Loading -> {
                                    _uploadState.emit(UploadWinnersState(operationState = OperationState.Loading))
                                }
                                is Resource.Success -> {
                                    dataStoreUseCases.saveOnWinnersUploadActionUseCase(true)
                                    _tournamentActionState.value = TournamentActionType.WinnersUpload
                                    _uploadState.emit(
                                        UploadWinnersState(
                                            operationState = OperationState.Success,
                                            message = UPLOAD_COMPLETED_MESSAGE
                                        )
                                    )
                                }
                                is Resource.Error -> {
                                    _uploadState.emit(
                                        UploadWinnersState(
                                            operationState = OperationState.Failed,
                                            message = result.message.toString()
                                        )
                                    )
                                }
                            }
                        } else {
                            _uploadState.emit(
                                UploadWinnersState(
                                    operationState = OperationState.Failed,
                                    message = CONNECTION_EXCEPTION_ERROR_MESSAGE
                                )
                            )
                        }
                    } else {
                        _uploadState.emit(
                            UploadWinnersState(
                                operationState = OperationState.Failed,
                                message = UNEXPECTED_EXCEPTION_ERROR_MESSAGE
                            )
                        )
                    }
                }
            }
        }
    }

    private suspend fun fillRoundOf16Games() {
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
        dataStoreUseCases.saveOnWinnersUploadActionUseCase(false)
        _tournamentActionState.value = TournamentActionType.GroupPhasePlayed
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
            if (_playoffCompletedState.value) {
                _tournamentActionState.value = TournamentActionType.FinalsFinished
                Log.d("HomeViewModel", "readOnWinnersUploadActionUseCase() ${TournamentActionType.FinalsFinished}")
                dataStoreUseCases.readOnWinnersUploadActionUseCase().collect {
                    if (it) {
                        Log.d("HomeViewModel", "readOnWinnersUploadActionUseCase() ${TournamentActionType.WinnersUpload}")
                        _tournamentActionState.value = TournamentActionType.WinnersUpload
                    }
                }
            }
        }
    }

    private fun getBestTeams() {
        viewModelScope.launch(Dispatchers.IO) {
            _bestTeams.value = playoffsUseCases.getBestThreeTeamsUseCase()
        }
    }

    fun resetTournament() {
        _playoffs.value = emptyList()
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
            dataStoreUseCases.saveOnWinnersUploadActionUseCase(false)
            _tournamentActionState.value = TournamentActionType.TournamentRestarted
        }
    }

    private fun getFinalStandings() {
        viewModelScope.launch(Dispatchers.IO) {
            standingsUsesCases.calculateFinalStandingsUseCase()
        }
    }

}