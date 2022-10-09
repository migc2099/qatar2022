package com.migc.qatar2022.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Login : Screen("login_screen")
    object GroupDetails : Screen("group_details_screen")
    object Standings : Screen("standings_screen")
    object TeamsMapScreen : Screen("teams_map_screen")
}
