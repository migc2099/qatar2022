package com.migc.qatar2022.domain.use_case.playoffs

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
        val champion = teamRepository.getTeamById(finalGame.winnerTeam)
        val runnerUp = teamRepository.getTeamById(finalGame.loserTeam)
        val thirdPlace = teamRepository.getTeamById(thirdPlaceGame.winnerTeam)
        return arrayOf(
            Team(champion.teamId, champion.name, champion.flagLocation, 0, 0),
            Team(runnerUp.teamId, runnerUp.name, runnerUp.flagLocation, 0, 0),
            Team(thirdPlace.teamId, thirdPlace.name, thirdPlace.flagLocation, 0, 0)
        )
    }
}