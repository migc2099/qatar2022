package com.migc.qatar2022.presentation.screens.login.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.presentation.screens.login.LoginViewModel

@Composable
fun SignInWithGoogle(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHomeScreen: (signedIn: Boolean) -> Unit
) {
    when(val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
        is Resource.Loading -> CircularProgressIndicator()
        is Resource.Success -> signInWithGoogleResponse.data?.let { signedIn ->
            LaunchedEffect(signedIn) {
                navigateToHomeScreen(signedIn)
            }
        }
        is Resource.Error -> LaunchedEffect(Unit) {
            print(signInWithGoogleResponse.message)
        }
    }
}