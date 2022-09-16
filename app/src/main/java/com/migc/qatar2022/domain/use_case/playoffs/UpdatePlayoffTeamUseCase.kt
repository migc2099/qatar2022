package com.migc.qatar2022.domain.use_case.playoffs

import com.migc.qatar2022.domain.repository.PlayoffsRepository

class UpdatePlayoffTeamUseCase(
    private val repository: PlayoffsRepository
) {

    suspend operator fun invoke(
        teamId: String,
        groupKey: String,
        position: Int
    ): Int {

        var roundKey = 0
        val isFirstPlace = position == 1

        when (groupKey) {
            "A" -> roundKey = if (isFirstPlace) 49 else 51
            "B" -> roundKey = if (isFirstPlace) 51 else 49
            "C" -> roundKey = if (isFirstPlace) 50 else 52
            "D" -> roundKey = if (isFirstPlace) 52 else 50
            "E" -> roundKey = if (isFirstPlace) 53 else 55
            "F" -> roundKey = if (isFirstPlace) 55 else 53
            "G" -> roundKey = if (isFirstPlace) 54 else 56
            "H" -> roundKey = if (isFirstPlace) 56 else 54
        }

        return if (isFirstPlace) {
            repository.updateFirstTeam(roundKey, teamId)
        } else {
            repository.updateSecondTeam(roundKey, teamId)
        }
    }

}