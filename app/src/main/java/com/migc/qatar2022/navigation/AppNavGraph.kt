package com.migc.qatar2022.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.migc.qatar2022.presentation.screens.home.HomeScreen
import com.migc.qatar2022.presentation.screens.splash.SplashScreen

@Composable
fun AppNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navHostController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navHostController = navHostController)
        }
    }
}