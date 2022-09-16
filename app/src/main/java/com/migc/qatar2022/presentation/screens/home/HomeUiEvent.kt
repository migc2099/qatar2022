package com.migc.qatar2022.presentation.screens.home

import com.migc.qatar2022.domain.model.Playoff

sealed class HomeUiEvent {

    object OnStart : HomeUiEvent()
    data class OnNavigateToGroupDetails(val listIndex: Int, val scrollOffSet: Int) : HomeUiEvent()
    data class OnPlayoffDialogClicked(val roundKey: Int): HomeUiEvent()
    data class OnPlayoffDialogCompleted(val playoff: Playoff): HomeUiEvent()

}