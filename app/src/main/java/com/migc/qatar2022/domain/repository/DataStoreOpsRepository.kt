package com.migc.qatar2022.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOpsRepository {
    suspend fun saveOnFixtureSetupCompleted(completed: Boolean)
    fun readOnFixtureSetupCompleted(): Flow<Boolean>
    suspend fun saveOnStandingsSetupCompleted(completed: Boolean)
    fun readOnStandingsSetupCompleted(): Flow<Boolean>
    suspend fun saveOnGroupsSetupCompleted(completed: Boolean)
    fun readOnGroupsSetupCompleted(): Flow<Boolean>
    suspend fun saveOnTeamsSetupCompleted(completed: Boolean)
    fun readOnTeamsSetupCompleted(): Flow<Boolean>
    suspend fun saveOnWinnerUploadActionCompleted(completed: Boolean)
    fun readOnWinnerUploadActionCompleted(): Flow<Boolean>
}