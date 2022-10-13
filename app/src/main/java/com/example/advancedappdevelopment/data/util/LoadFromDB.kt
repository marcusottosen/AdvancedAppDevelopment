package com.example.advancedappdevelopment.data.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.firebaseAdapter.VehicleDBModel
import kotlinx.coroutines.delay
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

@Composable
fun LoadFromDB(navController: NavController){
    //val db = Firebase.firestore

    val vehicles = VehicleDBModel()
    val vehiclesLoading: Boolean by vehicles.loading.observeAsState(initial = true)
    vehicles.loadVehiclesFromDB(navController)

    println(vehiclesLoading)
    if (vehiclesLoading){
        println("Loading... Loading...")
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Column() {
                CircularProgressIndicator(color = colorResource(R.color.primary))
                Button(onClick = {
                    navController.navigate(NavigationRoute.Homepage.route)
                }) {
                    Text(text = "Temporary start button")
                }
            }
        }
    } else {
        println("navigating to home...")
        //navController.navigate(NavigationRoute.Checkout.route)
    }
}