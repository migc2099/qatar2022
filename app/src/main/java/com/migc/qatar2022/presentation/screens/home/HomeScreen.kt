package com.migc.qatar2022.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.migc.qatar2022.presentation.components.CountDownDisplay

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth()
        ) {
            CountDownDisplay()
        }
    }
}