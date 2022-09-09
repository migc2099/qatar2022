package com.migc.qatar2022.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOpsRepository {
    suspend fun saveOnFirstTimeAppOpened(completed: Boolean)
    fun readOnFirstTimeAppOpened(): Flow<Boolean>
}