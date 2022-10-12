package com.example.advancedappdevelopment.data.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.ui.view.pages.CarInfo
import com.example.advancedappdevelopment.ui.view.pages.Checkout
import com.example.advancedappdevelopment.ui.view.pages.AvailableVehiclesPage


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController) {
    println("navcontroller")
    NavHost(navController, startDestination = NavigationRoute.Homepage.route
    /*if (Firebase.auth.currentUser != null)
        NavigationRoute.LoadFromDB.route
    else
        "authenticationOption"*/
    ) {


        composable(NavigationRoute.Homepage.route) {
            println("(nav to HomePage)")
            AvailableVehiclesPage(navController)
        }
        composable(NavigationRoute.CarInfo.route){
            println("(nav to CarInfo)")
            CarInfo(navController)
        }
        //Load before homepage is shown
        composable(NavigationRoute.LoadFromDB.route) {
            println("loading from db (go to loadFromDB)")
            LoadFromDB(navController)
        }
        composable(NavigationRoute.Checkout.route){
            println("(nav to Checkout)")
            Checkout(navController)
        }

    }
}
// Change page with argument: check last project