package com.migc.qatar2022.presentation.screens.splash

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.migc.qatar2022.R
import com.migc.qatar2022.navigation.Screen
import com.migc.qatar2022.ui.theme.mainColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val onFixtureSetupCompleted by splashViewModel.onFixtureSetupCompleted.collectAsState()
    val onStandingsSetupCompleted by splashViewModel.onStandingsSetupCompleted.collectAsState()
    val onGroupsSetupCompleted by splashViewModel.onGroupsSetupCompleted.collectAsState()
    val onTeamsSetupCompleted by splashViewModel.onTeamsSetCompleted.collectAsState()
    val degrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        Log.d("SplashScreen", "onFixtureSetupCompleted=$onFixtureSetupCompleted")
        if (!onFixtureSetupCompleted) {
            splashViewModel.setDatabaseFixture()
        }
        Log.d("SplashScreen", "onStandingsSetupCompleted=$onStandingsSetupCompleted")
        if (!onStandingsSetupCompleted) {
            splashViewModel.setDatabaseStandings()
        }
        Log.d("SplashScreen", "onGroupsSetupCompleted=$onGroupsSetupCompleted")
        if (!onGroupsSetupCompleted) {
            splashViewModel.setDatabaseGroups()
        }
        Log.d("SplashScreen", "onTeamsSetupCompleted=$onTeamsSetupCompleted")
        if (!onTeamsSetupCompleted) {
            splashViewModel.setDatabaseTeams()
        }
        delay(200)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    Splash(degrees = degrees.value)
}

@Composable
fun Splash(degrees: Float) {
    Box(
        modifier = Modifier
            .background(mainColor)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.rotate(degrees = degrees),
            painter = painterResource(id = R.drawable.ic_soccer_ball),
            contentDescription = stringResource(id = R.string.soccer_logo)
        )
    }
}

@Composable
@Preview
fun SplashScreenView() {
    Splash(degrees = 0f)
}