package com.migc.qatar2022.domain.use_case.playoffs

import android.util.Log
import com.migc.qatar2022.common.Constants.FINAL_STAGE
import com.migc.qatar2022.common.Constants.ROUND_OF_8_STAGE
import com.migc.qatar2022.common.Constants.SEMI_FINAL_STAGE
import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.repository.PlayoffsRepository
import com.migc.qatar2022.domain.repository.StandingsRepository

class UpdatePlayoffResultsUseCase(
    private val playoffsRepository: PlayoffsRepository,
    private val standingsRepository: StandingsRepository
) {

    suspend operator fun invoke(playoff: Playoff) {
        Log.d("UpdatePlayoffResultsUseCase", "after winner ${playoff.winnerTeam}")
        Log.d("UpdatePlayoffResultsUseCase", "after loser ${playoff.loserTeam}")
        playoffsRepository.updatePlayoffResults(playoff = playoff)
        saveNextRound(playoff.roundKey, playoff.winnerTeam)
        saveFinals(playoff.roundKey, playoff.winnerTeam, playoff.loserTeam)
    }

    suspend operator fun invoke(formerWinnerTeam: String, newWinnerTeam: String, currentRoundKey: Int) {
        val playoffsPlayed = playoffsRepository.getPlayoffsByTeamId(formerWinnerTeam)
        Log.d("UpdatePlayoffResultsUseCase", "teamId $formerWinnerTeam, currentRoundKey=$currentRoundKey")
        Log.d("UpdatePlayoffResultsUseCase", "playoffsPlayed ${playoffsPlayed.size}")
        playoffsPlayed
            .filter {
                // filters out current round
                it.roundKey > currentRoundKey
            }
            .forEach {
                Log.d("EditWinnerTeamInAdvancedPlayoffsUseCase", it.toString())
                if (it.roundKey < 61) {
                    if (formerWinnerTeam == it.winnerTeam) {
                        Log.d("invoke", "teamId == it.winnerTeam ${formerWinnerTeam == it.winnerTeam}")
                        playoffsRepository.updateWinnerTeam(it.roundKey, newWinnerTeam)
                        saveNextRound(it.roundKey, newWinnerTeam)
                    } else if (formerWinnerTeam == it.loserTeam) {
                        Log.d("invoke", "teamId == it.loserTeam ${formerWinnerTeam == it.loserTeam}")
                        playoffsRepository.updateLoserTeam(it.roundKey, newWinnerTeam)
                    }
                } else {
                    if (formerWinnerTeam == it.firstTeam) {
                        Log.d("invoke", "teamId == it.firstTeam ${formerWinnerTeam == it.firstTeam}")
                        playoffsRepository.updateFirstTeam(it.roundKey, newWinnerTeam)
                    }
                    if (formerWinnerTeam == it.loserTeam) {
                        Log.d("invoke", "teamId == it.loserTeam ${formerWinnerTeam == it.loserTeam}")
                        playoffsRepository.updateLoserTeam(it.roundKey, newWinnerTeam)
                    }
                    if (formerWinnerTeam == it.winnerTeam) {
                        Log.d("invoke", "teamId == it.winnerTeam ${formerWinnerTeam == it.winnerTeam}")
                        playoffsRepository.updateWinnerTeam(it.roundKey, newWinnerTeam)
                    }
                    if (formerWinnerTeam == it.secondTeam) {
                        Log.d("invoke", "teamId == it.secondTeam ${formerWinnerTeam == it.secondTeam}")
                        playoffsRepository.updateSecondTeam(it.roundKey, newWinnerTeam)
                    }
                }
            }
    }

    private suspend fun saveNextRound(currentRound: Int, teamId: String) {
        var nextRoundKey = 0
        when (currentRound) {
            49, 50 -> {
                nextRoundKey = 57
            }
            51, 52 -> nextRoundKey = 59
            53, 54 -> nextRoundKey = 58
            55, 56 -> nextRoundKey = 60
            57, 58 -> nextRoundKey = 61
            59, 60 -> nextRoundKey = 62
        }
        when (currentRound) {
            in 49..56 -> {
                standingsRepository.updateTeamStage(teamId, ROUND_OF_8_STAGE)
            }
            in 57..60 -> {
                standingsRepository.updateTeamStage(teamId, SEMI_FINAL_STAGE)
            }
            in 61..62 -> {
                standingsRepository.updateTeamStage(teamId, FINAL_STAGE)
            }
        }

        if (nextRoundKey != 0) {
            if (currentRound.mod(2) != 0) {
                playoffsRepository.updateFirstTeam(roundKey = nextRoundKey, teamId = teamId)
            } else {
                playoffsRepository.updateSecondTeam(roundKey = nextRoundKey, teamId = teamId)
            }
        }
    }

    private suspend fun saveFinals(currentRound: Int, winnerTeamId: String, loserTeamId: String) {
        when (currentRound) {
            61 -> {
                playoffsRepository.updateFirstTeam(roundKey = 63, teamId = loserTeamId)
                playoffsRepository.updateFirstTeam(roundKey = 64, teamId = winnerTeamId)
            }
            62 -> {
                playoffsRepository.updateSecondTeam(roundKey = 63, teamId = loserTeamId)
                playoffsRepository.updateSecondTeam(roundKey = 64, teamId = winnerTeamId)
            }
        }
    }

}