package com.migc.qatar2022.domain.use_case.playoffs

import android.util.Log
import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.repository.PlayoffsRepository

class UpdatePlayoffResultsUseCase(
    private val repository: PlayoffsRepository
) {

    suspend operator fun invoke(playoff: Playoff) {

        val roundKey = playoff.roundKey
        val firstTeam = playoff.firstTeam
        val secondTeam = playoff.secondTeam
        val firstTeamScore = playoff.firstTeamScore!!
        val secondTeamScore = playoff.secondTeamScore!!
        var firstTeamPkScore = 0
        var secondTeamPkScore = 0
        if (firstTeamScore == secondTeamScore) {
            firstTeamPkScore = playoff.firstTeamPKScore!!
            secondTeamPkScore = playoff.secondTeamPKScore!!
        }
        var loserTeam = ""
        var winnerTeam = ""

        Log.d("UpdatePlayoffResultsUseCase", "before firstTeamScore ${firstTeamScore}")
        Log.d("UpdatePlayoffResultsUseCase", "before secondTeamScore $secondTeamScore")

        when (firstTeamScore.compareTo(secondTeamScore)) {
            0 -> {
                Log.d("score compare result", "0")
                when (firstTeamPkScore.compareTo(secondTeamPkScore)) {
                    in 0..Int.MAX_VALUE -> {
                        Log.d("pk score compare result", "+")
                        winnerTeam = firstTeam
                        loserTeam = secondTeam
                    }
                    in 0 downTo Int.MIN_VALUE -> {
                        Log.d("pk score compare result", "-")
                        winnerTeam = secondTeam
                        loserTeam = firstTeam
                    }
                    else -> {
                        Log.d("pk score compare result", "0")
                    }
                }
            }
            in 0..Int.MAX_VALUE -> {
                Log.d("test", "+")
                winnerTeam = firstTeam
                loserTeam = secondTeam
            }
            else -> {
                Log.d("test", "-")
                winnerTeam = secondTeam
                loserTeam = firstTeam
            }
        }

        val completedPlayoff = Playoff(
            roundKey = roundKey,
            firstTeam = firstTeam,
            secondTeam = secondTeam,
            firstTeamScore = firstTeamScore,
            secondTeamScore = secondTeamScore,
            firstTeamPKScore = firstTeamPkScore,
            secondTeamPKScore = secondTeamPkScore,
            loserTeam = loserTeam,
            winnerTeam = winnerTeam
        )

        Log.d("UpdatePlayoffResultsUseCase", "after winner $winnerTeam")
        Log.d("UpdatePlayoffResultsUseCase", "after loser $loserTeam")
        repository.updatePlayoffResults(playoff = completedPlayoff)
        saveNextRound(roundKey, winnerTeam)
        saveFinals(roundKey, winnerTeam, loserTeam)
    }

    private suspend fun saveNextRound(currentRound: Int, teamId: String) {
        var nextRoundKey = 0
        when (currentRound) {
            49, 50 -> nextRoundKey = 57
            51, 52 -> nextRoundKey = 59
            53, 54 -> nextRoundKey = 58
            55, 56 -> nextRoundKey = 60
            57, 58 -> nextRoundKey = 61
            59, 60 -> nextRoundKey = 62
        }

        if (nextRoundKey != 0) {
            if (currentRound.mod(2) != 0) {
                repository.updateFirstTeam(roundKey = nextRoundKey, teamId = teamId)
            } else {
                repository.updateSecondTeam(roundKey = nextRoundKey, teamId = teamId)
            }
        }
    }

    private suspend fun saveFinals(currentRound: Int, winnerTeamId: String, loserTeamId: String) {
        when (currentRound) {
            61 -> {
                repository.updateFirstTeam(roundKey = 63, teamId = loserTeamId)
                repository.updateFirstTeam(roundKey = 64, teamId = winnerTeamId)
            }
            62 -> {
                repository.updateSecondTeam(roundKey = 63, teamId = loserTeamId)
                repository.updateSecondTeam(roundKey = 64, teamId = winnerTeamId)
            }
        }
    }

}