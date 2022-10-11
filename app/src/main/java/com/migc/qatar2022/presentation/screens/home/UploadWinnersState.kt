package com.migc.qatar2022.presentation.screens.home

data class TransactionState(
    val inProgress: Boolean = false,
    val success: Boolean = false,
    val failed: Boolean = false,
    val errorType: ErrorType = ErrorType.NoError
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
    Loading,
    Success,
    Failed
}

enum class ErrorType{
    NoError,
    NoInternet,
    UnknownError
}

