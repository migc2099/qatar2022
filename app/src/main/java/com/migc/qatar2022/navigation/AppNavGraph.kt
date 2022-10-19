package com.migc.qatar2022.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.migc.qatar2022.common.Constants.PARAM_GROUP_ID
import com.migc.qatar2022.presentation.screens.group_details.GroupDetailsScreen
import com.migc.qatar2022.presentation.screens.home.HomeScreen
import com.migc.qatar2022.presentation.screens.login.LoginScreen
import com.migc.qatar2022.presentation.screens.splash.SplashScreen
import com.migc.qatar2022.presentation.screens.standings.StandingScreen
import com.migc.qatar2022.presentation.screens.teams_map.TeamsMapScreen

@ExperimentalMaterialApi
@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    onLoadInterstitial: (String) -> Unit,
    onShowInterstitial: () -> Unit,
    onLoadRewardedInterstitial: () -> Unit,
    onShowRewardedInterstitial: () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navHostController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(
                navHostController = navHostController,
                onLoadInterstitial = onLoadInterstitial,
                onShowInterstitial = onShowInterstitial,
            )
        }
        composable(route = Screen.Login.route) {
            LoginScreen(
                navHostController = navHostController,
                onLoadInterstitial = onLoadInterstitial,
                onShowInterstitial = onShowInterstitial
            )
        }
        composable(route = Screen.GroupDetails.route + "/{$PARAM_GROUP_ID}") {
            GroupDetailsScreen(navHostController = navHostController)
        }
        composable(route = Screen.Standings.route) {
            StandingScreen()
        }
        composable(route = Screen.TeamsMapScreen.route) {
            TeamsMapScreen(
                onLoadInterstitial = onLoadInterstitial,
                onLoadRewardedInterstitial = onLoadRewardedInterstitial,
                onShowInterstitial = onShowInterstitial,
                onShowRewardedInterstitial = onShowRewardedInterstitial
            )
        }
    }
}