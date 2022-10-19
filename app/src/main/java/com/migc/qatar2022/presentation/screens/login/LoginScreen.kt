package com.migc.qatar2022.presentation.screens.login

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.migc.qatar2022.R
import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.presentation.screens.login.components.OneTapSignIn
import com.migc.qatar2022.presentation.screens.login.components.SignInButton
import com.migc.qatar2022.presentation.screens.login.components.SignInWithGoogle
import com.migc.qatar2022.ui.theme.*

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoadInterstitial: (String) -> Unit,
    onShowInterstitial: () -> Unit
) {
    val mContext = LocalContext.current
    val authState = loginViewModel.auth.collectAsState()
    val anonymousButtonVisible = remember { mutableStateOf(true) }
    val googleButtonVisible = remember { mutableStateOf(true) }
    val tooManyClicks = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        loginViewModel.toastMessage.collect {
            var message = ""
            when (it) {
                Constants.SIGN_IN_EXCEPTION_TRY_AGAIN_MESSAGE -> message =
                    mContext.getString(R.string.message_sign_in_unexpected_error)
                Constants.CONNECTION_EXCEPTION_ERROR_MESSAGE -> message = mContext.getString(R.string.message_connection_error)
                Constants.UNEXPECTED_EXCEPTION_ERROR_MESSAGE -> message = mContext.getString(R.string.message_unexpected_error)
                Constants.SIGN_IN_SUCCESS_MESSAGE -> message = mContext.getString(R.string.sign_in_success_message)
                Constants.SIGN_OUT_SUCCESS_MESSAGE -> message = mContext.getString(R.string.sign_out_success_message)
            }
            if (message.isNotEmpty()) {
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(key1 = authState.value) {
        Log.d("LaunchEffect", "authState $authState")
        when (authState.value.signInMethod) {
            SignInMethod.GoogleSignIn -> {
                anonymousButtonVisible.value = false
                googleButtonVisible.value = true
            }
            SignInMethod.AnonymousSignIn -> {
                anonymousButtonVisible.value = true
                googleButtonVisible.value = false
            }
            SignInMethod.Undefined -> {
                anonymousButtonVisible.value = true
                googleButtonVisible.value = true
            }
        }
    }

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
                val buttonText = remember { mutableStateOf("") }
                Log.d("LoginScreen", "authState.value.user = ${authState.value.user}")
                if (authState.value.user != null) {
                    buttonText.value = "Sign out"
                } else {
                    buttonText.value = "Sign in anonymously"
                }
                if (anonymousButtonVisible.value) {
                    Box(contentAlignment = Alignment.Center) {
                        SignInButton(
                            text = buttonText.value,
                            isEnabled = !authState.value.inProgress
                        ) {
                            tooManyClicks.value++
                            if (tooManyClicks.value < Constants.WARN_NUMBER_LOGIN_ATTEMPTS) {
                                onLoadInterstitial(Constants.AD_UNIT_LOGIN_INTERSTITIAL_ID)
                                Log.d("LoginScreen", "authState.value.user = ${authState.value.user}")
                                if (authState.value.user != null) {
                                    loginViewModel.onEvent(LoginUiEvent.OnSignOutClicked)
                                } else {
                                    loginViewModel.onEvent(LoginUiEvent.OnSignInAnonymouslyClicked)
                                }
                            } else {
                                onShowInterstitial()
                            }

                        }
                        if (authState.value.inProgress) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(SMALL_CIRCULAR_PROGRESS_SIZE),
                                color = mainBackgroundColor
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(EXTRA_LARGE_PADDING))
                if (googleButtonVisible.value) {
                    Box(contentAlignment = Alignment.Center) {
                        var text = stringResource(id = R.string.sign_in_with_google_text)
                        if (authState.value.user != null) {
                            text = "Sign out"
                        }
                        SignInButton(
                            text = text,
                            icon = painterResource(id = R.drawable.ic_authenticate),
                            isEnabled = !authState.value.inProgress
                        ) {
                            tooManyClicks.value++
                            if (authState.value.user != null) {
                                loginViewModel.onEvent(LoginUiEvent.OnOneTapSignOutClicked)
                            } else {
                                loginViewModel.onEvent(LoginUiEvent.OnOneTapSignInClicked)
                            }

                        }
                        if (authState.value.inProgress) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(SMALL_CIRCULAR_PROGRESS_SIZE),
                                color = mainBackgroundColor
                            )
                        }
                    }
                }
            }
        },
        backgroundColor = mainBackgroundColor
    )

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val credentials = loginViewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                loginViewModel.onEvent(LoginUiEvent.OnSignInWithGoogle(googleCredentials))
            } catch (it: ApiException) {
                print(it)
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(
        launch = {
            launch(it)
        }
    )

    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                Log.d("SignInWithGoogle", "Signed In")
//                navigateToProfileScreen()
            }
        }
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
