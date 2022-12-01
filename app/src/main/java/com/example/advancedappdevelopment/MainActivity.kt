package com.example.advancedappdevelopment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.advancedappdevelopment.data.util.Navigation
import com.example.advancedappdevelopment.ui.theme.AdvancedAppDevelopmentTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            MainScreen()
            AdvancedAppDevelopmentTheme {
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Navigation(navController = navController)
}