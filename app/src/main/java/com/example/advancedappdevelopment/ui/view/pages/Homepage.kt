package com.example.advancedappdevelopment.ui.view.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute

@Composable
fun Homepage(navController: NavController){
    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
    ){
        Text(text = "HOME")
        Button(onClick = {
            navController.navigate(NavigationRoute.CarInfo.route)
        }) {
            Text(text = "carinfo")
        }
        Button(onClick = {
            navController.navigate(NavigationRoute.Checkout.route)
        }) {
            Text(text = "checkout")
        }
    }
}