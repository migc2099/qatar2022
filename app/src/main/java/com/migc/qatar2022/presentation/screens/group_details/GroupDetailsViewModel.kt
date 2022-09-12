package com.migc.qatar2022.presentation.screens.group_details

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.qatar2022.common.Constants.PARAM_GROUP_ID
import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.domain.use_case.GroupDetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    private fun getFixture(groupId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("getFixture", "groupId: $groupId")
            _selectedGroup.value = useCases.getFixtureByGroupUseCase(group = groupId)
            editableFixture = _selectedGroup.value.toMutableList()
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
    fun onSaveButtonClicked(group: String) {
        Log.d("GroupDetailsViewModel","onSaveButtonClicked" )
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("GroupDetailsViewModel","currentGroup: ${currentGroup.value.last()}" )
            useCases.calculatePointsUseCase(editableFixture, group.last().toString())
        }
    }

}