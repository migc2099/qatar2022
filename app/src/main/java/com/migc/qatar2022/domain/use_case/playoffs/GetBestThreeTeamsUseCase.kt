package com.migc.qatar2022.domain.use_case.playoffs

import com.migc.qatar2022.data.local.mapper.toTeam
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.domain.repository.PlayoffsRepository
import com.migc.qatar2022.domain.repository.TeamRepository

class GetBestThreeTeamsUseCase(
    private val playoffRepository: PlayoffsRepository,
    private val teamRepository: TeamRepository
) {
    suspend operator fun invoke(): Array<Team> {
        val finalGame = playoffRepository.getPlayoffByRoundKey(64)
        val thirdPlaceGame = playoffRepository.getPlayoffByRoundKey(63)

        if (finalGame.winnerTeam.isNotEmpty() && finalGame.loserTeam.isNotEmpty() && thirdPlaceGame.winnerTeam.isNotEmpty()) {
            val champion = teamRepository.getTeamById(finalGame.winnerTeam).toTeam()
            val runnerUp = teamRepository.getTeamById(finalGame.loserTeam).toTeam()
            val thirdPlace = teamRepository.getTeamById(thirdPlaceGame.winnerTeam).toTeam()
            return arrayOf(
                Team(champion.teamId, champion.teamName, champion.flagUri, 0, 0),
                Team(runnerUp.teamId, runnerUp.teamName, runnerUp.flagUri, 0, 0),
                Team(thirdPlace.teamId, thirdPlace.teamName, thirdPlace.flagUri, 0, 0)
            )
        }
        return emptyArray()
    }
}