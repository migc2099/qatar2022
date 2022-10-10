package com.migc.qatar2022.presentation.screens.home

data class TransactionState(
    val inProgress: Boolean = false,
    val success: Boolean = false,
    val failed: Boolean = false,
    val lastTournamentActionType: TournamentActionType = TournamentActionType.Undefined
)

enum class TournamentActionType{
    Undefined,
    TournamentRestarted,
    GroupPhasePlayed,
    PlayoffPlayed,
    FinalsFinished,
    WinnersUpload
}

