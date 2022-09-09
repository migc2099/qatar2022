package com.migc.qatar2022.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.migc.qatar2022.common.Constants.PREFERENCES_NAME
import com.migc.qatar2022.domain.repository.DataStoreOpsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_NAME
)

class DataStoreOpsRepositoryImpl(context: Context) : DataStoreOpsRepository {

    val dataStore = context.dataStore

    private object PreferencesKey {
        val onFirstTimeAppOpenedKey = booleanPreferencesKey(name = PREFERENCES_NAME)
    }

    override suspend fun saveOnFirstTimeAppOpened(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onFirstTimeAppOpenedKey] = completed
        }
    }

    override fun readOnFirstTimeAppOpened(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onFirstTimeAppOpened = preferences[PreferencesKey.onFirstTimeAppOpenedKey] ?: false
                onFirstTimeAppOpened
            }
    }

}