package com.migc.qatar2022.domain.repository

interface DatabaseSetupRepository {

    suspend fun setupGroupsFixture():Boolean
    suspend fun setupStandings():Boolean

}