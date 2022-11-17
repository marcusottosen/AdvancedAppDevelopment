package com.example.advancedappdevelopment.data.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import com.example.advancedappdevelopment.ui.view.pages.CarInfo
import com.example.advancedappdevelopment.ui.view.pages.Checkout
import com.example.advancedappdevelopment.ui.view.pages.AvailableVehiclesPage
import com.example.advancedappdevelopment.ui.view.pages.login.LoginPage
import com.example.advancedappdevelopment.ui.view.pages.login.LoginRegisterPage
import com.example.advancedappdevelopment.ui.view.pages.login.RegisterPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


//@ExperimentalMaterialApi
//@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController) {
    println("navcontroller")

    // Uncomment for at blive logget ud hver gang app'en genstartes
    //FirebaseAuth.getInstance().signOut()

    //var startingDestination
    val startingDestination = if (FirebaseAuth.getInstance().currentUser != null) {
        NavigationRoute.LoadFromDB.route
    } else {
        NavigationRoute.LoginRegisterPage.route
    }

    NavHost(navController, startDestination = startingDestination
    /*if (Firebase.auth.currentUser != null)
        NavigationRoute.LoadFromDB.route
    else
        "authenticationOption"*/
    ) {


        composable(NavigationRoute.Homepage.route) {
            println("(nav to HomePage)")
            AvailableVehiclesPage(navController)
        }
        /*composable(NavigationRoute.CarInfo.route){
            println("(nav to CarInfo)")
            CarInfo(navController)
        }*/
        //Load before homepage is shown
        composable(NavigationRoute.LoadFromDB.route) {
            println("Calling VehicleDB")
            //val vehicles = VehicleDBModel()
            //vehicles.loadVehiclesFromDB(navController)
            LoadFromDB(navController)
        }
        composable(NavigationRoute.Checkout.route){
            println("(nav to Checkout)")
            Checkout(navController)
        }
        composable(NavigationRoute.CarInfo.route) {
            val eventModel =
                navController.previousBackStackEntry?.arguments?.getParcelable<Vehicle>("vehicle")
            eventModel?.let {
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