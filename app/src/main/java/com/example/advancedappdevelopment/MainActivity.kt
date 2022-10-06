package com.example.advancedappdevelopment

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.compose.rememberNavController
import com.example.advancedappdevelopment.data.util.Navigation
import com.example.advancedappdevelopment.ui.theme.AdvancedAppDevelopmentTheme


class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvancedAppDevelopmentTheme {
                MainScreen()
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }*/
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
        Navigation(navController = navController)
}