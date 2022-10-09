package com.migc.qatar2022.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.use_case.FirebaseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseUseCases: FirebaseUseCases
) : ViewModel() {

    private val _auth: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val auth = _auth.asStateFlow()

    init {
        _auth.value = LoginState(inProgress = true)
        _auth.value = LoginState(inProgress = false, user = firebaseUseCases.getFirebaseAuthUseCase())
    }

    fun onEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {
            is LoginUiEvent.OnSignInAnonymouslyClicked -> {
                firebaseUseCases.signInAnonymouslyUseCase().onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _auth.value = LoginState(user = result.data)
                        }
                        is Resource.Error -> {
                            _auth.value = LoginState(error = result.message.toString())
                        }
                        is Resource.Loading -> {
                            _auth.value = LoginState(inProgress = true)
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is LoginUiEvent.OnSignOutClicked -> {
                firebaseUseCases.signOutUseCase().onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            if (result.data == true) {
                                _auth.value = LoginState(user = null)
                            }
                        }
                        is Resource.Error -> {
                            _auth.value = LoginState(error = result.message.toString())
                        }
                        is Resource.Loading -> {
                            _auth.value = LoginState(inProgress = true)
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

}