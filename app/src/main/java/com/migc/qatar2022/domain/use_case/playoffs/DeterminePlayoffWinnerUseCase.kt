package com.migc.qatar2022.domain.use_case.playoffs

import android.util.Log
import com.migc.qatar2022.domain.model.Playoff

class DeterminePlayoffWinnerUseCase{

    operator fun invoke(playoff: Playoff): Playoff {
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

        Log.d("UpdatePlayoffResultsUseCase", "before firstTeamScore $firstTeamScore")
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

        return Playoff(
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
    }

}