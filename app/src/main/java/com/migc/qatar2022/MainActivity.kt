package com.migc.qatar2022

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.migc.qatar2022.navigation.AppNavGraph
import com.migc.qatar2022.ui.theme.Qatar2022Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Qatar2022Theme {
                navHostController = rememberNavController()
                AppNavGraph(navHostController = navHostController)
            }
        }
    }
}