package com.migc.qatar2022.presentation.screens.home

sealed class HomeUiEvent {

    object OnStart : HomeUiEvent()
    data class OnNavigateToGroupDetails(val listIndex: Int, val scrollOffSet: Int) : HomeUiEvent()

}