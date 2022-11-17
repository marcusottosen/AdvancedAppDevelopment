package com.example.advancedappdevelopment.data.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import com.example.advancedappdevelopment.ui.view.pages.CarInfo
import com.example.advancedappdevelopment.ui.view.pages.Checkout
import com.example.advancedappdevelopment.ui.view.pages.AvailableVehiclesPage
import com.example.advancedappdevelopment.ui.view.pages.Login.LoginPage
import com.example.advancedappdevelopment.ui.view.pages.Login.RegisterPage
import com.example.advancedappdevelopment.ui.view.pages.Login.LoginRegisterPage

@Composable
fun Navigation(navController: NavHostController) {
    println("navcontroller")
    NavHost(navController, startDestination = NavigationRoute.LoadFromDB.route
    /*if (Firebase.auth.currentUser != null)
        NavigationRoute.LoadFromDB.route
    else
        "authenticationOption"*/
    ) {


        composable(NavigationRoute.Homepage.route) {
            AvailableVehiclesPage(navController)
        }

        //Load before homepage is shown
        composable(NavigationRoute.LoadFromDB.route) {
            LoadFromDB(navController)
        }
        composable(NavigationRoute.Checkout.route){
            val carModel =
                navController.previousBackStackEntry?.arguments?.getParcelable<Vehicle>("vehicle")
            carModel?.let {
                Checkout(vehicle = it, navController = navController)
            }
        }



        composable(NavigationRoute.CarInfo.route) {
            val carModel =
                navController.previousBackStackEntry?.arguments?.getParcelable<Vehicle>("vehicle")
            carModel?.let {
                CarInfo(vehicle = it, navController = navController)
            }
        }

        // Authentication pages
        composable(NavigationRoute.LoginPage.route) {
            LoginPage(navController)
        }
        composable(NavigationRoute.RegisterPage.route) {
            RegisterPage(navController)
        }
        composable(NavigationRoute.LoginRegisterPage.route) {
            LoginRegisterPage(navController)
        }




    }
}
// Change page with argument: check last project