package com.migc.qatar2022.presentation.screens.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.qatar2022.domain.use_case.DataStoreUseCases
import com.migc.qatar2022.domain.use_case.DatabaseSetupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreUseCases: DataStoreUseCases,
    private val databaseSetupUseCases: DatabaseSetupUseCases
) : ViewModel() {

    private val _onFixtureSetupCompleted = MutableStateFlow(false)
    val onFixtureSetupCompleted: StateFlow<Boolean> = _onFixtureSetupCompleted
    private val _onStandingsSetupCompleted = MutableStateFlow(false)
    val onStandingsSetupCompleted = _onStandingsSetupCompleted
    private val _onGroupsSetupCompleted = MutableStateFlow(false)
    val onGroupsSetupCompleted = _onGroupsSetupCompleted
    private val _onTeamsSetupCompleted = MutableStateFlow(false)
    val onTeamsSetCompleted = _onTeamsSetupCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onFixtureSetupCompleted.value =
                dataStoreUseCases.readOnFixtureSetupUseCase().stateIn(viewModelScope).value
            _onStandingsSetupCompleted.value =
                dataStoreUseCases.readOnStandingsSetupUseCase().stateIn(viewModelScope).value
            _onGroupsSetupCompleted.value =
                dataStoreUseCases.readOnGroupsSetupUseCase().stateIn(viewModelScope).value
            _onTeamsSetupCompleted.value =
                dataStoreUseCases.readOnTeamsSetupUseCase().stateIn(viewModelScope).value

        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun setDatabaseFixture() {
        Log.d("SplashViewModel", "setDatabaseFixture() called")
        GlobalScope.launch(Dispatchers.IO) {
            val insertResult = databaseSetupUseCases.setGroupsFixtureUseCase()
            if (insertResult) {
                Log.d("SplashViewModel", "setDatabaseFixture() rows inserted successfully")
                dataStoreUseCases.saveOnFixtureSetupUseCase(completed = true)
            } else {
                Log.e("SplashViewModel", "setDatabaseFixture() didn't insert rows")
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun setDatabaseStandings() {
        GlobalScope.launch(Dispatchers.IO) {
            val insertResult = databaseSetupUseCases.setStandingsUseCase()
            if (insertResult) {
                Log.d("SplashViewModel", "setDatabaseStandings() rows inserted successfully")
                dataStoreUseCases.saveOnStandingsSetupUseCase(completed = true)
            } else {
                Log.e("SplashViewModel", "setDatabaseStandings() didn't insert rows")
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun setDatabaseGroups(){
        GlobalScope.launch(Dispatchers.IO){
            val insertResult = databaseSetupUseCases.setGroupsUseCase()
            if (insertResult) {
                Log.d("SplashViewModel", "setDatabaseGroups() rows inserted successfully")
                dataStoreUseCases.saveOnGroupsSetupUseCase(completed = true)
            } else {
                Log.e("SplashViewModel", "setDatabaseGroups() didn't insert rows")
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun setDatabaseTeams() {
        GlobalScope.launch(Dispatchers.IO) {
            val insertResult = databaseSetupUseCases.setTeamsUseCase()
            if (insertResult) {
                Log.d("SplashViewModel", "setDatabaseTeams() rows inserted successfully")
                dataStoreUseCases.saveOnTeamsSetupUseCase(completed = true)
            } else {
                Log.e("SplashViewModel", "setDatabaseTeams() didn't insert rows")
            }
        }
    }

}