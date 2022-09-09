package com.migc.qatar2022.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.domain.model.Group
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    var groups: List<Group> = TeamsData.allGroups

}