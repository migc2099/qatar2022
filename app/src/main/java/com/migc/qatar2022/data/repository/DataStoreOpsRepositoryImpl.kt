package com.migc.qatar2022.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.migc.qatar2022.common.Constants.PREFERENCES_GROUPS_FIXTURE_KEY
import com.migc.qatar2022.common.Constants.PREFERENCES_GROUPS_KEY
import com.migc.qatar2022.common.Constants.PREFERENCES_NAME
import com.migc.qatar2022.common.Constants.PREFERENCES_STANDINGS_KEY
import com.migc.qatar2022.common.Constants.PREFERENCES_TEAMS_KEY
import com.migc.qatar2022.domain.repository.DataStoreOpsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_NAME
)

class DataStoreOpsRepositoryImpl(context: Context) : DataStoreOpsRepository {

    private val dataStore = context.dataStore

    private object PreferencesKey {
        val onFixtureSetupCompletedKey = booleanPreferencesKey(name = PREFERENCES_GROUPS_FIXTURE_KEY)
        val onStandingsSetupCompletedKey = booleanPreferencesKey(name = PREFERENCES_STANDINGS_KEY)
        val onGroupsSetupCompletedKey = booleanPreferencesKey(name = PREFERENCES_GROUPS_KEY)
        val onTeamsSetupCompletedKey = booleanPreferencesKey(name = PREFERENCES_TEAMS_KEY)
    }

    override suspend fun saveOnFixtureSetupCompleted(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onFixtureSetupCompletedKey] = completed
        }
    }

    override fun readOnFixtureSetupCompleted(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onFirstTimeAppOpened = preferences[PreferencesKey.onFixtureSetupCompletedKey] ?: false
                onFirstTimeAppOpened
            }
    }

    override suspend fun saveOnStandingsSetupCompleted(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onStandingsSetupCompletedKey] = completed
        }
    }

    override fun readOnStandingsSetupCompleted(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onStandingsSetupCompleted = preferences[PreferencesKey.onStandingsSetupCompletedKey] ?: false
                onStandingsSetupCompleted
            }
    }

    override suspend fun saveOnGroupsSetupCompleted(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onGroupsSetupCompletedKey] = completed
        }
    }

    override fun readOnGroupsSetupCompleted(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onGroupSetupCompleted = preferences[PreferencesKey.onGroupsSetupCompletedKey] ?: false
                onGroupSetupCompleted
            }
    }

    override suspend fun saveOnTeamsSetupCompleted(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onTeamsSetupCompletedKey] = completed
        }
    }

    override fun readOnTeamsSetupCompleted(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onTeamsSetupCompleted = preferences[PreferencesKey.onTeamsSetupCompletedKey] ?: false
                onTeamsSetupCompleted
            }
    }

}