package com.migc.qatar2022.presentation.screens.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.migc.qatar2022.R
import com.migc.qatar2022.ui.theme.*

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val authState = loginViewModel.auth.collectAsState()
    Scaffold(
        topBar = {
            LoginTopBar(
                onClick = { navHostController.popBackStack() }
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(
                        onClick = {
                            Log.d("LoginScreen", "authState.value.user = ${authState.value.user}")
                            if (authState.value.user != null) {
                                loginViewModel.onEvent(LoginUiEvent.OnSignOutClicked)
                            } else {
                                loginViewModel.onEvent(LoginUiEvent.OnSignInAnonymouslyClicked)
                            }
                        },
                        enabled = !authState.value.inProgress,
                        shape = RoundedCornerShape(EXTRA_LARGE_ROUND_CORNER),
                        colors = ButtonDefaults.buttonColors(backgroundColor = mainColor)
                    ) {
                        Log.d("LoginScreen", "authState.value.inProgress = ${authState.value.inProgress}")
                        if (authState.value.inProgress) {
                            CircularProgressIndicator(modifier = Modifier.size(SMALL_CIRCULAR_PROGRESS_SIZE))
                        } else {
                            Log.d("LoginScreen", "authState.value.user = ${authState.value.user}")
                            if (authState.value.user != null) {
                                Text(
                                    modifier = Modifier.padding(MEDIUM_PADDING),
                                    text = "Sign out",
                                    color = mainBackgroundColor
                                )
                            } else {
                                Text(
                                    modifier = Modifier.padding(MEDIUM_PADDING),
                                    text = "Sign in anonymously",
                                    color = mainBackgroundColor
                                )
                            }
                        }
                    }
                }
            }
        },
        backgroundColor = mainBackgroundColor
    )
}

@Composable
fun LoginTopBar(onClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Login",
                color = mainBackgroundColor
            )
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(
                onClick = {
                    onClick()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_go_back),
                    stringResource(id = R.string.go_back_text),
                    tint = mainBackgroundColor
                )
            }
        },
        backgroundColor = mainColor,
        contentColor = mainBackgroundColor,
        elevation = LARGE_ELEVATION
    )

}
