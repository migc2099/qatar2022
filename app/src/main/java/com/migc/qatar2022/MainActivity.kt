package com.migc.qatar2022

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.migc.qatar2022.domain.use_case.StandingsUseCases
import com.migc.qatar2022.presentation.screens.StandingsViewModel
import com.migc.qatar2022.presentation.screens.TeamsScreen
import com.migc.qatar2022.ui.theme.Qatar2022Theme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Qatar2022Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val show = remember { mutableStateOf(false) }
    Button(
        onClick = {
            show.value = true
        }
    ){
        Text(text = "Show")
    }
    if (show.value){
        TeamsScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Qatar2022Theme {
        Greeting("Android")
    }
}