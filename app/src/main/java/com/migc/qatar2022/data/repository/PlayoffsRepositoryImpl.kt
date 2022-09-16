package com.migc.qatar2022.data.repository

import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.data.local.mapper.toPlayoff
import com.migc.qatar2022.data.local.mapper.toPlayoffEntity
import com.migc.qatar2022.domain.model.Playoff
import com.migc.qatar2022.domain.repository.PlayoffsRepository

class PlayoffsRepositoryImpl constructor(
    private val qatarDatabase: QatarDatabase
) : PlayoffsRepository {

    override fun setPlayoffs(): List<Playoff> {
        return listOf(
            Playoff(49),
            Playoff(50),
            Playoff(51),
            Playoff(52),
            Playoff(53),
            Playoff(54),
            Playoff(55),
            Playoff(56),
            Playoff(57),
            Playoff(58),
            Playoff(59),
            Playoff(60),
            Playoff(61),
            Playoff(62),
            Playoff(63),
            Playoff(64)
        )
    }

    override suspend fun getPlayoffByRoundKey(roundKey: Int): Playoff {
        return qatarDatabase.playoffDao.getPlayoffByRoundKey(roundKey).toPlayoff()
    }

    override suspend fun getAllPlayoffs(): List<Playoff> {
        val playoffEntities = qatarDatabase.playoffDao.getAllPlayoffs()
        return playoffEntities.map {
            it.toPlayoff()
        }
    }

    override suspend fun resetPlayoffs() {
        val playoffEntities = setPlayoffs().map {
            it.toPlayoffEntity()
        }
        qatarDatabase.playoffDao.resetAllPlayoffs(playoffEntities)
    }

    override suspend fun updatePlayoffResults(playoff: Playoff): Int {
        return qatarDatabase.playoffDao.updateGameResults(playoff.toPlayoffEntity())
    }

    override suspend fun updateFirstTeam(roundKey: Int, teamId: String): Int {
        return qatarDatabase.playoffDao.updateFirstTeam(roundKey, teamId)
    }

    override suspend fun updateSecondTeam(roundKey: Int, teamId: String): Int {
        return qatarDatabase.playoffDao.updateSecondTeam(roundKey, teamId)
    }

    override suspend fun updateWinnerTeam(roundKey: Int, teamId: String) {
        qatarDatabase.playoffDao.updateWinnerTeam(roundKey, teamId)
    }

    override suspend fun updateLoserTeam(roundKey: Int, teamId: String) {
        qatarDatabase.playoffDao.updateLoserTeam(roundKey, teamId)
    }

    override suspend fun updatePlayoffs(playoffs: List<Playoff>) {
        val finalsEntities = playoffs.map {
            it.toPlayoffEntity()
        }
        qatarDatabase.playoffDao.updatePlayoffs(finalsEntities)
    }

    override suspend fun getPlayoffsByTeamId(teamId: String): List<Playoff> {
        return qatarDatabase.playoffDao.getFinishedPlayoffsByTeamId(teamId)
            .map {
                it.toPlayoff()
            }
    }

}