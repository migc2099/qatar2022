package com.migc.qatar2022.domain.use_case.standings

import android.util.Log
import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.model.TeamStat
import com.migc.qatar2022.domain.repository.PlayoffsRepository
import com.migc.qatar2022.domain.repository.StandingsRepository

class CalculateFinalStandingsUseCase(
    private val standingsRepository: StandingsRepository,
    private val playoffsRepository: PlayoffsRepository
) {

    suspend operator fun invoke(): List<TeamStat> {
        val finalStatsList: MutableList<TeamStat> = mutableListOf()
        val fourths = standingsRepository.getTeamsByGroupPosition(4)
        val thirds = standingsRepository.getTeamsByGroupPosition(3)
        val groupSeconds = standingsRepository.getTeamsByGroupPosition(2)
        val groupFirsts = standingsRepository.getTeamsByGroupPosition(1)

        val final = playoffsRepository.getPlayoffByRoundKey(64)
        val thirdPlacePlayoff = playoffsRepository.getPlayoffByRoundKey(63)

        val allPlayoffs = playoffsRepository.getAllPlayoffs()

        val roundOf16sPlayoffs = allPlayoffs.filter {
            it.roundKey in 49..56
        }
        val roundOf8sPlayoffs = allPlayoffs.filter {
            it.roundKey in 57..60
        }
        val semifinals = allPlayoffs.filter {
            it.roundKey in 61..62
        }
        val finals = allPlayoffs.filter {
            it.roundKey in 63..64
        }

        val roundOf16Stats = calculatePointsByRound(roundOf16sPlayoffs)
        val roundOf8Stats = calculatePointsByRound(roundOf8sPlayoffs)
        val semifinalStats = calculatePointsByRound(semifinals)
        val finalStats = calculatePointsByRound(finals)

        val allPlayoffsStats = combineStatsPerTeam(roundOf16Stats, roundOf8Stats, semifinalStats, finalStats)

        // combines stats of teams that played playoffs and group stage (1st and 2nd places)
        val combined1 = combineStatsPerTeam(allPlayoffsStats, groupFirsts)
        val completePlayoffsStatsPerTeam = combineStatsPerTeam(combined1, groupSeconds)

        completePlayoffsStatsPerTeam.forEach {
            Log.d("TEST completePlayoffsStatsPerTeam", "${it.teamId}, Points: ${it.points}, Goals: ${it.goalsInFavor} - ${it.goalsAgainst}")
        }

        val firstPlace = getTeamStatFromCompletePlayoffs(final.winnerTeam, completePlayoffsStatsPerTeam)
        val secondPlace = getTeamStatFromCompletePlayoffs(final.loserTeam, completePlayoffsStatsPerTeam)
        val thirdPlace = getTeamStatFromCompletePlayoffs(thirdPlacePlayoff.winnerTeam, completePlayoffsStatsPerTeam)
        val fourthPlace = getTeamStatFromCompletePlayoffs(thirdPlacePlayoff.loserTeam, completePlayoffsStatsPerTeam)
        val final8List = roundOf8sPlayoffs.map { playoff ->
            getTeamStatFromCompletePlayoffs(playoff.loserTeam, completePlayoffsStatsPerTeam)
        }.sortedWith(
            compareBy<TeamStat>(
                { it.points },
                { it.goalsInFavor - it.goalsAgainst },
                { it.goalsInFavor }).reversed()
        )
        val final16List = roundOf16sPlayoffs.map { playoff ->
            getTeamStatFromCompletePlayoffs(playoff.loserTeam, completePlayoffsStatsPerTeam)
        }.sortedWith(
            compareBy<TeamStat>(
                { it.points },
                { it.goalsInFavor - it.goalsAgainst },
                { it.goalsInFavor }).reversed()
        )

        finalStatsList.add(firstPlace)
        finalStatsList.add(secondPlace)
        finalStatsList.add(thirdPlace)
        finalStatsList.add(fourthPlace)
        finalStatsList.addAll(final8List)
        finalStatsList.addAll(final16List)
        finalStatsList.addAll(thirds)
        finalStatsList.addAll(fourths)
        return finalStatsList
    }

    private fun calculatePointsByRound(playoffs: List<Playoff>): List<TeamStat> {
        val stats: MutableList<TeamStat> = mutableListOf()
        playoffs.let { list ->
            if (list.isNotEmpty()) {
                list.forEach {
                    when (it.firstTeamScore!!.compareTo(it.secondTeamScore!!)) {
                        0 -> {
                            Log.d("score compare result", "0")
                            when (it.firstTeamPKScore!!.compareTo(it.secondTeamPKScore!!)) {
                                // first team wins by penalties
                                in 0..Int.MAX_VALUE -> {
                                    Log.d("pk score compare result", "+")
                                    stats.add(
                                        TeamStat(
                                            teamId = it.firstTeam,
                                            groupKey = "",
                                            groupPosition = 0,
                                            gamesPlayed = 1,
                                            wins = 0,
                                            draws = 1,
                                            loses = 0,
                                            goalsAgainst = it.secondTeamScore!!,
                                            goalsInFavor = it.firstTeamScore!!,
                                            points = 2
                                        )
                                    )
                                    stats.add(
                                        TeamStat(
                                            teamId = it.secondTeam,
                                            groupKey = "",
                                            groupPosition = 0,
                                            gamesPlayed = 1,
                                            wins = 0,
                                            draws = 1,
                                            loses = 0,
                                            goalsAgainst = it.firstTeamScore!!,
                                            goalsInFavor = it.secondTeamScore!!,
                                            points = 1
                                        )
                                    )
                                }
                                // second team wins by penalties
                                in 0 downTo Int.MIN_VALUE -> {
                                    Log.d("pk score compare result", "-")
                                    stats.add(
                                        TeamStat(
                                            teamId = it.secondTeam,
                                            groupKey = "",
                                            groupPosition = 0,
                                            gamesPlayed = 1,
                                            wins = 0,
                                            draws = 1,
                                            loses = 0,
                                            goalsAgainst = it.firstTeamScore!!,
                                            goalsInFavor = it.secondTeamScore!!,
                                            points = 2
                                        )
                                    )
                                    stats.add(
                                        TeamStat(
                                            teamId = it.firstTeam,
                                            groupKey = "",
                                            groupPosition = 0,
                                            gamesPlayed = 1,
                                            wins = 0,
                                            draws = 1,
                                            loses = 0,
                                            goalsAgainst = it.secondTeamScore!!,
                                            goalsInFavor = it.firstTeamScore!!,
                                            points = 1
                                        )
                                    )
                                }
                                else -> {
                                    Log.d("pk score compare result", "0")
                                }
                            }
                        }
                        // first team wins in game
                        in 0..Int.MAX_VALUE -> {
                            Log.d("test", "+")
                            stats.add(
                                TeamStat(
                                    teamId = it.firstTeam,
                                    groupKey = "",
                                    groupPosition = 0,
                                    gamesPlayed = 1,
                                    wins = 1,
                                    draws = 0,
                                    loses = 0,
                                    goalsAgainst = it.secondTeamScore!!,
                                    goalsInFavor = it.firstTeamScore!!,
                                    points = 3
                                )
                            )
                            stats.add(
                                TeamStat(
                                    teamId = it.secondTeam,
                                    groupKey = "",
                                    groupPosition = 0,
                                    gamesPlayed = 1,
                                    wins = 0,
                                    draws = 0,
                                    loses = 1,
                                    goalsAgainst = it.firstTeamScore!!,
                                    goalsInFavor = it.secondTeamScore!!,
                                    points = 0
                                )
                            )
                        }
                        // second teams wins in game
                        else -> {
                            Log.d("test", "-")
                            stats.add(
                                TeamStat(
                                    teamId = it.secondTeam,
                                    groupKey = "",
                                    groupPosition = 0,
                                    gamesPlayed = 1,
                                    wins = 1,
                                    draws = 0,
                                    loses = 0,
                                    goalsAgainst = it.firstTeamScore!!,
                                    goalsInFavor = it.secondTeamScore!!,
                                    points = 3
                                )
                            )
                            stats.add(
                                TeamStat(
                                    teamId = it.firstTeam,
                                    groupKey = "",
                                    groupPosition = 0,
                                    gamesPlayed = 1,
                                    wins = 0,
                                    draws = 0,
                                    loses = 1,
                                    goalsAgainst = it.secondTeamScore!!,
                                    goalsInFavor = it.firstTeamScore!!,
                                    points = 0
                                )
                            )
                        }
                    }
                }
            }

        }
        return stats
    }

    private fun combineStatsPerTeam(
        playoffStats: List<TeamStat>,
        list1: List<TeamStat>,
        list2: List<TeamStat> = emptyList(),
        list3: List<TeamStat> = emptyList()
    ): List<TeamStat> {
        playoffStats.map { team ->
            list1.forEach {
                if (team.teamId == it.teamId) {
                    team.points += it.points
                    team.goalsInFavor += it.goalsInFavor
                    team.goalsAgainst += it.goalsAgainst
                    team.wins += it.wins
                    team.draws += it.draws
                    team.loses += it.loses
                }
            }
            list2.forEach {
                if (team.teamId == it.teamId) {
                    team.points += it.points
                    team.goalsInFavor += it.goalsInFavor
                    team.goalsAgainst += it.goalsAgainst
                    team.wins += it.wins
                    team.draws += it.draws
                    team.loses += it.loses
                }
            }
            list3.forEach {
                if (team.teamId == it.teamId) {
                    team.points += it.points
                    team.goalsInFavor += it.goalsInFavor
                    team.goalsAgainst += it.goalsAgainst
                    team.wins += it.wins
                    team.draws += it.draws
                    team.loses += it.loses
                }
            }
        }
        return playoffStats
    }

    private fun getTeamStatFromCompletePlayoffs(teamId: String, playoffStats: List<TeamStat>): TeamStat {
        return playoffStats.first {
            it.teamId == teamId
        }
    }

}