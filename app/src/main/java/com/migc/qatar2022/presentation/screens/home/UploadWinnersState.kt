package com.migc.qatar2022.presentation.screens.home

data class UploadWinnersState(
    val operationState: OperationState = OperationState.Idle,
    val message: String = ""
)

enum class TournamentActionType{
    Undefined,
    TournamentRestarted,
    GroupPhasePlayed,
    PlayoffPlayed,
    FinalsFinished,
    WinnersUpload
}

enum class OperationState{
    Idle,
    InternetChecked,
    Loading,
    Success,
    Failed
}