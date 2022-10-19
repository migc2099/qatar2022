package com.migc.qatar2022.presentation.screens.home

import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.model.Team

sealed class HomeUiEvent {

    object OnStart : HomeUiEvent()
    data class OnNavigateToGroupDetails(val listIndex: Int, val scrollOffSet: Int) : HomeUiEvent()
    data class OnPlayoffDialogClicked(val roundKey: Int): HomeUiEvent()
    data class OnPlayoffDialogCompleted(val playoff: Playoff): HomeUiEvent()
    object OnPlayoffDialogDismissed: HomeUiEvent()
    object OnResetPlayoffsClicked: HomeUiEvent()
    object OnShowStandingsClicked: HomeUiEvent()
    data class OnUploadWinnersClicked(val winners: List<Team>): HomeUiEvent()

}