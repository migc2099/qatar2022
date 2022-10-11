package com.migc.qatar2022.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.use_case.FirebaseUseCases
import com.migc.qatar2022.domain.use_case.NetworkUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseUseCases: FirebaseUseCases,
    private val networkUseCases: NetworkUseCases
) : ViewModel() {

    private val _auth: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val auth = _auth.asStateFlow()

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    init {
        _auth.value = LoginState(inProgress = true)
        _auth.value = LoginState(inProgress = false, user = firebaseUseCases.getFirebaseAuthUseCase())
    }

    fun onEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {
            is LoginUiEvent.OnSignInAnonymouslyClicked -> {
                val result = networkUseCases.checkIfInternetAvailableUseCase()
                if (result){
                    firebaseUseCases.signInAnonymouslyUseCase().onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                _auth.value = LoginState(user = result.data)
                            }
                            is Resource.Error -> {
                                _auth.value = LoginState(error = result.message.toString())
                                displayToastMessage(result.message.toString())
                            }
                            is Resource.Loading -> {
                                _auth.value = LoginState(inProgress = true)
                            }
                        }
                    }.launchIn(viewModelScope)
                } else {
                    displayToastMessage(Constants.CONNECTION_EXCEPTION_ERROR_MESSAGE)
                }

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

    private fun displayToastMessage(message: String) {
        viewModelScope.launch {
            _toastMessage.emit(message)
        }
    }

}