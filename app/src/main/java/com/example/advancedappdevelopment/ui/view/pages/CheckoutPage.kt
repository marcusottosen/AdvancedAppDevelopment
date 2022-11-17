package com.example.advancedappdevelopment.ui.view.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle

@Composable
fun Checkout(vehicle: Vehicle, navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(text = "Checkout")

        Button(onClick = {
            navController.navigate(NavigationRoute.Homepage.route)
        }) {
            Text(text = "Home")
        }
        Button(onClick = {
            navController.navigate(NavigationRoute.CarInfo.route)
        }) {
            Text(text = "carinfo")
        }
    }
}