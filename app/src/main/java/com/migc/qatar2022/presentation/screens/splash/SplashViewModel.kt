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
import kotlin.math.log

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreUseCases: DataStoreUseCases,
    private val databaseSetupUseCases: DatabaseSetupUseCases
) : ViewModel() {

    private val _onFixtureSetupCompleted = MutableStateFlow(false)
    val onFixtureSetupCompleted: StateFlow<Boolean> = _onFixtureSetupCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onFixtureSetupCompleted.value =
                dataStoreUseCases.readOnFixtureSetupUseCase().stateIn(viewModelScope).value
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

}