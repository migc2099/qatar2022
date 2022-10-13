package com.migc.qatar2022.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.presentation.screens.login.LoginViewModel

@Composable
fun OneTapSignIn(
    viewModel: LoginViewModel = hiltViewModel(),
    launch: (result: BeginSignInResult) -> Unit
) {
    val mContext = LocalContext.current
    when (val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is Resource.Loading -> CircularProgressIndicator()
        is Resource.Success -> oneTapSignInResponse.data?.let {
            LaunchedEffect(it) {
                launch(it)
            }
        }
        is Resource.Error -> LaunchedEffect(Unit) {
            Toast.makeText(mContext, oneTapSignInResponse.message, Toast.LENGTH_SHORT).show()
        }
    }
}