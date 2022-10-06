package com.migc.qatar2022.presentation.screens.group_details

sealed class GroupDetailUiEvent{
    object OnSaveChangesClicked: GroupDetailUiEvent()
    object OnGenerateScoredClicked: GroupDetailUiEvent()
}
