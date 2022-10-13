package com.migc.qatar2022.presentation.screens.login

import com.google.firebase.auth.AuthCredential

sealed class LoginUiEvent {

    object OnSignOutClicked : LoginUiEvent()
    object OnSignInAnonymouslyClicked : LoginUiEvent()
    object OnOneTapSignInClicked: LoginUiEvent()
    data class OnSignInWithGoogle(val credential: AuthCredential): LoginUiEvent()
    object OnOneTapSignOutClicked: LoginUiEvent()

}