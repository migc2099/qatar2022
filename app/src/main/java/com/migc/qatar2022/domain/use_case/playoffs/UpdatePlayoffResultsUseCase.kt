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

    // Registers next round's firstTeam and secondTeam fields only
    suspend operator fun invoke(playoff: Playoff) {
        Log.d("UpdatePlayoffResultsUseCase", "after winner ${playoff.winnerTeam}")
        Log.d("UpdatePlayoffResultsUseCase", "after loser ${playoff.loserTeam}")
        // updates the current playoff
        playoffsRepository.updatePlayoffResults(playoff = playoff)
        registerNextRound(playoff.roundKey, playoff.winnerTeam)
        registerFinals(playoff.roundKey, playoff.winnerTeam, playoff.loserTeam)
    }

    // Searches and changes firstTeam, secondTeam, winnerTeam, and loserTeam on playoffs
    // that were already registered previously
    suspend operator fun invoke(formerWinnerTeam: String, newWinnerTeam: String, currentRoundKey: Int) {
        val playoffsPlayed = playoffsRepository.getPlayoffsByTeamId(formerWinnerTeam)
        Log.d("UpdatePlayoffResultsUseCase", "teamId $formerWinnerTeam, currentRoundKey=$currentRoundKey")
        Log.d("UpdatePlayoffResultsUseCase", "playoffsPlayed ${playoffsPlayed.size}")
        Log.d("UpdatePlayoffResultsUseCase", "playoffsPlayed $playoffsPlayed")
        playoffsPlayed
            .filter {
                // filters out current round
                it.roundKey > currentRoundKey
            }
            .forEach {
                Log.d("EditWinnerTeamInAdvancedPlayoffsUseCase", it.toString())
                when (it.roundKey) {
                    in 49..64 -> {
                        Log.d("Round key:", it.roundKey.toString())
                        if (it.roundKey == 63 || it.roundKey == 64) {
                            if (it.firstTeam == formerWinnerTeam) {
                                playoffsRepository.updateFirstTeam(it.roundKey, newWinnerTeam)
                            } else if (it.secondTeam == formerWinnerTeam) {
                                playoffsRepository.updateSecondTeam(it.roundKey, newWinnerTeam)
                            }
                        }
                        if (formerWinnerTeam == it.winnerTeam) {
                            Log.d("invoke", "teamId == it.winnerTeam ${formerWinnerTeam == it.winnerTeam}")
                            playoffsRepository.updateWinnerTeam(it.roundKey, newWinnerTeam)
                            registerNextRound(it.roundKey, newWinnerTeam)
                        } else if (formerWinnerTeam == it.loserTeam) {
                            Log.d("invoke", "teamId == it.loserTeam ${formerWinnerTeam == it.loserTeam}")
                            playoffsRepository.updateLoserTeam(it.roundKey, newWinnerTeam)
                        }

                    }
                }
            }

        if (currentRoundKey == 61 || currentRoundKey == 62) {
            updateThirdPlacePlayoff(
                existingTeam = newWinnerTeam,
                replacingTeam = formerWinnerTeam
            )
        }

    }

    private suspend fun registerNextRound(currentRound: Int, teamId: String) {
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

    private suspend fun registerFinals(currentRound: Int, winnerTeamId: String, loserTeamId: String) {
        Log.d("registerFinals", "winnerTeamId: $winnerTeamId - loserTeamId: $loserTeamId")
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

    private suspend fun updateThirdPlacePlayoff(existingTeam: String, replacingTeam: String) {
        val oldPlayoff = playoffsRepository.getPlayoffByRoundKey(63)
        if (oldPlayoff.winnerTeam == existingTeam) {
            playoffsRepository.updateWinnerTeam(oldPlayoff.roundKey, replacingTeam)
        } else if (oldPlayoff.loserTeam == existingTeam) {
            playoffsRepository.updateLoserTeam(oldPlayoff.roundKey, replacingTeam)
        }
        if (oldPlayoff.firstTeam == existingTeam) {
            playoffsRepository.updateFirstTeam(oldPlayoff.roundKey, replacingTeam)
        } else if (oldPlayoff.secondTeam == existingTeam) {
            playoffsRepository.updateSecondTeam(oldPlayoff.roundKey, replacingTeam)
        }
    }

}