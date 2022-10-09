package com.migc.qatar2022.presentation.screens.login

sealed class LoginUiEvent {

    object OnSignOutClicked : LoginUiEvent()
    object OnSignInAnonymouslyClicked : LoginUiEvent()

}