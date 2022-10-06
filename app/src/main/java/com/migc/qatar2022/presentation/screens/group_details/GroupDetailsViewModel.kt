package com.migc.qatar2022.presentation.screens.group_details

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.qatar2022.common.Constants.PARAM_GROUP_ID
import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.domain.use_case.GroupDetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GroupDetailsViewModel @Inject constructor(
    private val useCases: GroupDetailsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedGroup: MutableStateFlow<List<Fixture>> = MutableStateFlow(emptyList())
    val selectedGroup: StateFlow<List<Fixture>> = _selectedGroup

    lateinit var editableFixture: MutableList<Fixture>
    val currentGroup = mutableStateOf("")

    init {
        savedStateHandle.get<String>(PARAM_GROUP_ID)?.let { groupId ->
            currentGroup.value = groupId
            getFixture(groupId)
        }
    }

    fun onEvent(event: GroupDetailUiEvent) {
        when (event) {
            is GroupDetailUiEvent.OnSaveChangesClicked -> {
                saveScores()
            }
            is GroupDetailUiEvent.OnGenerateScoredClicked -> {
                generateWeightedResult()
            }
        }
    }

    private fun generateWeightedResult() {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.calculateWeightedResultUseCase(matches = _selectedGroup.value)
                .collect {
                    Log.d("generateWeight", it.toString())
                    _selectedGroup.value = it
                    editableFixture = it.toMutableList()
                }
        }
    }

    private fun getFixture(groupId: String) {
        Log.d("getFixture", "groupId: $groupId")
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getFixtureByGroupUseCase(group = groupId)
                .collect {
                    _selectedGroup.value = it
                    editableFixture = it.toMutableStateList()
                }
        }
    }

    fun updateHomeTeamScore(matchNumber: Int, score: Int) {
        editableFixture
            .filter {
                it.matchNumber == matchNumber
            }.map {
                it.homeTeamScore = score
            }
    }

    fun updateAwayTeamScore(matchNumber: Int, score: Int) {
        editableFixture
            .filter {
                it.matchNumber == matchNumber
            }.map {
                it.awayTeamScore = score
            }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun saveScores() {
        Log.d("GroupDetailsViewModel", "onSaveButtonClicked")
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("GroupDetailsViewModel", "currentGroup: ${currentGroup.value.last()}")
            useCases.calculatePointsUseCase(editableFixture, currentGroup.value.last().toString())
        }
    }

}