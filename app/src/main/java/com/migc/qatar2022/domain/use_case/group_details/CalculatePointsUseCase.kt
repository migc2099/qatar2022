package com.migc.qatar2022.domain.use_case.group_details

import android.util.Log
import com.migc.qatar2022.common.TeamsData
import com.migc.qatar2022.data.local.mapper.toTeamsStat
import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.domain.model.TeamStat
import com.migc.qatar2022.domain.repository.FixtureRepository
import com.migc.qatar2022.domain.repository.StandingsRepository

class CalculatePointsUseCase(
    private val fixtureRepository: FixtureRepository,
    private val standingsRepository: StandingsRepository
) {

    suspend operator fun invoke(results: MutableList<Fixture>, group: String) {
        val teamStats: MutableList<TeamStat> = TeamsData.standings
            .filter {
                it.groupKey == group
            }.map {
                it.toTeamsStat()
            }.toMutableList()

        teamStats.forEach {
            Log.d("CalculatePointsUseCase", "teamState $it")
        }
        val completedFixtures: MutableList<Fixture> = mutableListOf()
        Log.d("CalculatePointsUseCase", "teamStats size: ${teamStats.size}")
        Log.d("CalculatePointsUseCase", "resultsL $results")

        results.forEach { matchResult ->
            if (matchResult.homeTeamScore != null && matchResult.awayTeamScore != null) {
                Log.d("CalculatePointsUseCase", "homeTeamScore and awayTeamScore aren't null")
                Log.d("results:", "homeTeamScore=${matchResult.homeTeamScore} awayTeamScore=${matchResult.awayTeamScore}")
                when (matchResult.homeTeamScore!!.compareTo(matchResult.awayTeamScore!!)) {
                    0 -> {
                        Log.d("when 0", "matchResult: $matchResult")
                        teamStats
                            .filter { homeTeam ->
                                homeTeam.teamId == matchResult.homeTeam
                            }
                            .map { homeTeam ->
                                homeTeam.draws++
                                homeTeam.points++
                                homeTeam.gamesPlayed++
                                homeTeam.goalsInFavor = homeTeam.goalsInFavor + matchResult.homeTeamScore!!
                                homeTeam.goalsAgainst = homeTeam.goalsAgainst + matchResult.awayTeamScore!!
                            }
                        teamStats
                            .filter { awayTeam ->
                                awayTeam.teamId == matchResult.awayTeam
                            }
                            .map { awayTeam ->
                                awayTeam.draws++
                                awayTeam.points++
                                awayTeam.gamesPlayed++
                                awayTeam.goalsInFavor = awayTeam.goalsInFavor + matchResult.awayTeamScore!!
                                awayTeam.goalsAgainst = awayTeam.goalsAgainst + matchResult.homeTeamScore!!
                            }
                    }
                    in 0..Int.MAX_VALUE -> {
                        Log.d("when +", "matchResult: $matchResult")
                        teamStats
                            .filter { homeTeam ->
                                homeTeam.teamId == matchResult.homeTeam
                            }
                            .map { homeTeam ->
                                homeTeam.wins++
                                homeTeam.points = homeTeam.points + 3
                                homeTeam.gamesPlayed++
                                homeTeam.goalsInFavor = homeTeam.goalsInFavor + matchResult.homeTeamScore!!
                                homeTeam.goalsAgainst = homeTeam.goalsAgainst + matchResult.awayTeamScore!!
                            }
                        teamStats
                            .filter { awayTeam ->
                                awayTeam.teamId == matchResult.awayTeam
                            }
                            .map { awayTeam ->
                                awayTeam.loses++
                                awayTeam.gamesPlayed++
                                awayTeam.goalsInFavor = awayTeam.goalsInFavor + matchResult.awayTeamScore!!
                                awayTeam.goalsAgainst = awayTeam.goalsAgainst + matchResult.homeTeamScore!!
                            }
                    }
                    else -> {
                        Log.d("when else", "matchResult: $matchResult")
                        teamStats
                            .filter { homeTeam ->
                                homeTeam.teamId == matchResult.homeTeam
                            }
                            .map { homeTeam ->
                                homeTeam.loses++
                                homeTeam.gamesPlayed++
                                homeTeam.goalsInFavor = homeTeam.goalsInFavor + matchResult.homeTeamScore!!
                                homeTeam.goalsAgainst = homeTeam.goalsAgainst + matchResult.awayTeamScore!!
                            }
                        teamStats
                            .filter { awayTeam ->
                                awayTeam.teamId == matchResult.awayTeam
                            }
                            .map { awayTeam ->
                                awayTeam.wins++
                                awayTeam.points = awayTeam.points + 3
                                awayTeam.gamesPlayed++
                                awayTeam.goalsInFavor = awayTeam.goalsInFavor + matchResult.awayTeamScore!!
                                awayTeam.goalsAgainst = awayTeam.goalsAgainst + matchResult.homeTeamScore!!
                            }
                    }
                }
                completedFixtures.add(matchResult)
            } else if (matchResult.homeTeamScore == null || matchResult.awayTeamScore == null) {
                // This runs only when user modifier the score of only one team and left the other blank
                matchResult.homeTeamScore.let {
                    if (it != null) {
                        val singleFixture = Fixture(
                            matchNumber = matchResult.matchNumber,
                            roundNumber = matchResult.roundNumber,
                            group = matchResult.group,
                            date = matchResult.date,
                            location = matchResult.location,
                            homeTeam = matchResult.homeTeam,
                            awayTeam = matchResult.awayTeam,
                            homeTeamScore = it.toInt()
                        )
                        fixtureRepository.updateSingleFixture(singleFixture)
                    }
                }
                matchResult.awayTeamScore.let {
                    if (it != null) {
                        val singleFixture = Fixture(
                            matchNumber = matchResult.matchNumber,
                            roundNumber = matchResult.roundNumber,
                            group = matchResult.group,
                            date = matchResult.date,
                            location = matchResult.location,
                            homeTeam = matchResult.homeTeam,
                            awayTeam = matchResult.awayTeam,
                            awayTeamScore = it.toInt()
                        )
                        fixtureRepository.updateSingleFixture(singleFixture)
                    }
                }
            }
        }
        if (completedFixtures.isNotEmpty()) {
            teamStats.forEach {
                Log.d("CalculatePointsUseCase", "teamStat: $it")
            }
            fixtureRepository.updateFixture(completedFixtures)
            standingsRepository.insertTeams(teamStats)
        }
    }

}