package com.example.advancedappdevelopment.data.util

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.advancedappdevelopment.R
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.firebaseAdapter.AssociationDBModel
import com.example.advancedappdevelopment.data.model.firebaseAdapter.VehicleDBModel
import kotlinx.coroutines.*

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoadFromDB(navController: NavController)  {
    val scope = rememberCoroutineScope()

    val associations = AssociationDBModel()
    val associationsLoading: Boolean by associations.loading.observeAsState(initial = true)
    associations.loadAssociationsFromDB()

    val vehicles = VehicleDBModel()
    val vehiclesLoading: Boolean by vehicles.loading.observeAsState(initial = true)
    vehicles.loadVehiclesFromDB()


    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(color = colorResource(R.color.primary), modifier = Modifier.size(50.dp))
    }

    scope.launch {
        while (vehiclesLoading)
            delay(1000)
        scope.cancel()
        navController.navigate((NavigationRoute.Homepage.route))
    }
}
