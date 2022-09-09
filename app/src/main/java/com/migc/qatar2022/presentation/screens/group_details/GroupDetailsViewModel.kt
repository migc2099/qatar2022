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
import kotlinx.coroutines.Dispatchers
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

    val currentGroup = mutableStateOf("")

    init {
        savedStateHandle.get<String>(PARAM_GROUP_ID)?.let { groupId ->
            currentGroup.value = groupId
            getFixture(groupId)
        }
    }

    private fun getFixture(groupId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("getFixture", "groupId: ${groupId.last()}")
            _selectedGroup.value = useCases.getFixtureByGroupUseCase(group = groupId.last().toString())
        }

//        useCases.getMatchesByGroupUseCase(groupId).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    Log.d("GroupDetailsViewModel", "${result.data}")
//                    result.data.let {
//                        _state.value = GroupDetailsState(fixture = it!!)
//                    }
//                }
//                is Resource.Error -> {
//                    _state.value = GroupDetailsState(error = result.message ?: "An unexpected error occurred")
//                    Log.e("GroupDetailsViewModel", result.message.toString())
//                }
//                is Resource.Loading -> {
//                    Log.d("GroupDetailsViewModel", "Loading")
//                    _state.value = GroupDetailsState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
    }

}