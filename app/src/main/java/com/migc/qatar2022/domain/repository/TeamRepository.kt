package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.data.local.entity.TeamEntity

interface TeamRepository {

    suspend fun getTeamById(id: String): TeamEntity

}