package com.migc.qatar2022.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.use_case.FirebaseUseCases
import com.migc.qatar2022.domain.use_case.NetworkUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseUseCases: FirebaseUseCases,
    private val networkUseCases: NetworkUseCases,
    val oneTapClient: SignInClient
) : ViewModel() {

    private val _auth: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val auth = _auth.asStateFlow()

    var oneTapSignInResponse by mutableStateOf<Resource<BeginSignInResult>>(Resource.Loading(null))
        private set

    var signInWithGoogleResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))
        private set

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    init {
        _auth.value = LoginState(inProgress = true)
        _auth.value = LoginState(inProgress = false, user = firebaseUseCases.getFirebaseAuthUseCase())
        if (_auth.value.user != null) {
            if (_auth.value.user!!.isAnonymous) {
                _auth.value = _auth.value.copy(signInMethod = SignInMethod.AnonymousSignIn)
            } else {
                _auth.value = _auth.value.copy(signInMethod = SignInMethod.GoogleSignIn)
            }
        }
    }

    fun onEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {
            is LoginUiEvent.OnSignInAnonymouslyClicked -> {
                val isThereInternet = networkUseCases.checkIfInternetAvailableUseCase()
                if (isThereInternet) {
                    firebaseUseCases.signInAnonymouslyUseCase().onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                _auth.value = LoginState(user = result.data, signInMethod = SignInMethod.AnonymousSignIn)
                                displayToastMessage(Constants.SIGN_IN_SUCCESS_MESSAGE)
                            }
                            is Resource.Error -> {
                                _auth.value = LoginState(message = result.message.toString(), signInMethod = SignInMethod.Undefined)
                                displayToastMessage(result.message.toString())
                            }
                            is Resource.Loading -> {
                                _auth.value = LoginState(inProgress = true, signInMethod = SignInMethod.AnonymousSignIn)
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
                            displayToastMessage(Constants.SIGN_OUT_SUCCESS_MESSAGE)
                        }
                        is Resource.Error -> {
                            _auth.value = LoginState(message = result.message.toString())
                            displayToastMessage(result.message.toString())
                        }
                        is Resource.Loading -> {
                            _auth.value = LoginState(inProgress = true)
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is LoginUiEvent.OnOneTapSignInClicked -> {
                viewModelScope.launch {
                    _auth.value = LoginState(inProgress = true, signInMethod = SignInMethod.GoogleSignIn)
//                    oneTapSignInResponse = Resource.Loading()
                    oneTapSignInResponse = firebaseUseCases.oneTapSignInUseCase()
                }
            }
            is LoginUiEvent.OnSignInWithGoogle -> {
                viewModelScope.launch {
//                    oneTapSignInResponse = Resource.Loading()
                    _auth.value = LoginState(inProgress = true, signInMethod = SignInMethod.GoogleSignIn)
                    val result = firebaseUseCases.signInWithGoogleUseCase(loginUiEvent.credential)
                    when (result) {
                        is Resource.Loading -> {
                            _auth.value = LoginState(inProgress = true, signInMethod = SignInMethod.GoogleSignIn)
                        }
                        is Resource.Success -> {
                            _auth.value = LoginState(
                                inProgress = false,
                                user = result.data,
                                message = Constants.SIGN_IN_SUCCESS_MESSAGE,
                                signInMethod = SignInMethod.GoogleSignIn
                            )
                            displayToastMessage(Constants.SIGN_IN_SUCCESS_MESSAGE)
                        }
                        is Resource.Error -> {
                            _auth.value = LoginState(
                                inProgress = false,
                                message = result.message.toString()
                            )
                            displayToastMessage(result.message.toString())
                        }
                    }
//                    signInWithGoogleResponse = firebaseUseCases.signInWithGoogleUseCase(loginUiEvent.credential)
                }
            }
            is LoginUiEvent.OnOneTapSignOutClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val result = firebaseUseCases.oneTapSignOutUseCase()
                    when (result) {
                        is Resource.Loading -> {
                            _auth.value = LoginState(inProgress = true)
                        }
                        is Resource.Success -> {
                            _auth.value = LoginState(
                                inProgress = false,
                                user = null,
                                message = Constants.SIGN_OUT_SUCCESS_MESSAGE,
                                signInMethod = SignInMethod.Undefined
                            )
                            displayToastMessage(Constants.SIGN_OUT_SUCCESS_MESSAGE)
                        }
                        is Resource.Error -> {
                            _auth.value = LoginState(
                                inProgress = false,
                                message = result.message.toString()
                            )
                            displayToastMessage(result.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun displayToastMessage(message: String) {
        viewModelScope.launch {
            _toastMessage.emit(message)
        }
    }

}