package com.migc.qatar2022.presentation.screens.login

import com.google.firebase.auth.FirebaseUser

data class LoginState(
    val inProgress: Boolean = false,
    val user: FirebaseUser? = null,
    val error: String = ""
)