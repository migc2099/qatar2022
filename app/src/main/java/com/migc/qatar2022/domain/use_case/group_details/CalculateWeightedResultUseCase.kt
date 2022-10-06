package com.migc.qatar2022.domain.use_case.group_details

import android.util.Log
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.domain.model.Fixture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.internal.toImmutableList
import kotlin.math.absoluteValue
import kotlin.random.Random

class CalculateWeightedResultUseCase {
    operator fun invoke(matches: List<Fixture>): Flow<List<Fixture>> = flow {
        val results = mutableListOf<Fixture>()
        matches.forEach { fixture ->
            val firstTeamWeight = TeamsData.weightMap[fixture.homeTeam]!!
            val secondTeamWeight = TeamsData.weightMap[fixture.awayTeam]!!

            val randomNumber = Random.nextInt(0, 20)
            val firstRandom = (randomNumber * firstTeamWeight).toInt()
            val secondRandom = (randomNumber * secondTeamWeight).toInt()
            Log.d("Random", "firstRandom ${fixture.homeTeam} $firstRandom")
            Log.d("Random", "secondRandom ${fixture.awayTeam} $secondRandom")
            var firstScore = 0
            var secondScore = 0

            val diff1: Boolean = firstTeamWeight.minus(secondTeamWeight).absoluteValue >= 0.5
            val diff2: Boolean = firstTeamWeight.minus(secondTeamWeight).absoluteValue <= 0.15
            var goleada = 0
            if (diff1) {
                goleada = 1     // if team is too strong gets one more goal
            }
            var help = 0
            if (diff2) {
                help = 1        // if teams are closely strong help the weaker team with one more goal
            }

            if (firstRandom > secondRandom) {
                do {
                    firstScore = Random.nextInt(1, 3)
                    secondScore = Random.nextInt(0, 2)
                } while (firstScore <= secondScore)
                firstScore += goleada
                secondScore += help
            } else if (firstRandom < secondRandom) {
                do {
                    firstScore = Random.nextInt(0, 2)
                    secondScore = Random.nextInt(1, 3)
                } while (secondScore <= firstScore)
                secondScore += goleada
                firstScore += help
            } else {
                do {
                    firstScore = Random.nextInt(0, 4)
                    secondScore = Random.nextInt(0, 4)
                } while (firstScore == secondScore)
            }

            val fix = fixture.copy(homeTeamScore = firstScore, awayTeamScore = secondScore)
            results.add(fix)
        }
        emit(results.toImmutableList())
    }
}