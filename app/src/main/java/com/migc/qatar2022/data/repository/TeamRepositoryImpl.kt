package com.migc.qatar2022.data.repository

import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.data.local.entity.TeamEntity
import com.migc.qatar2022.domain.repository.TeamRepository

class TeamRepositoryImpl(
    private val qatarDatabase: QatarDatabase
):TeamRepository {

    override suspend fun getTeamById(id: String): TeamEntity {
        return qatarDatabase.teamDao.getTeamById(id)
    }

}