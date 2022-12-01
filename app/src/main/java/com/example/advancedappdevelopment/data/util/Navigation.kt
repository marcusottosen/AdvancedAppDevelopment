package com.example.advancedappdevelopment.data.util

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.data.model.dataClass.TempVehicle
import com.example.advancedappdevelopment.data.model.dataClass.Vehicle
import com.example.advancedappdevelopment.data.model.firebaseAdapter.updateCurrentUser
import com.example.advancedappdevelopment.data.model.updateHIW
import com.example.advancedappdevelopment.data.model.updateHelp
import com.example.advancedappdevelopment.ui.view.pages.AvailableVehiclesPage
import com.example.advancedappdevelopment.ui.view.pages.CarInfo
import com.example.advancedappdevelopment.ui.view.pages.Checkout
import com.example.advancedappdevelopment.ui.view.pages.CheckoutSuccess
import com.example.advancedappdevelopment.ui.view.pages.login.LoginPage
import com.example.advancedappdevelopment.ui.view.pages.login.LoginRegisterPage
import com.example.advancedappdevelopment.ui.view.pages.login.RegisterPage
import com.example.advancedappdevelopment.ui.view.pages.profile.ContactPage
import com.example.advancedappdevelopment.ui.view.pages.profile.HelpPage
import com.example.advancedappdevelopment.ui.view.pages.profile.HiwPage
import com.example.advancedappdevelopment.ui.view.pages.profile.ProfilePage
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Navigation(navController: NavHostController) {
    // Uncomment for at blive logget ud hver gang app'en genstartes
    //FirebaseAuth.getInstance().signOut()

    //var startingDestination
    val startingDestination = if (FirebaseAuth.getInstance().currentUser != null) {
        updateCurrentUser()
        NavigationRoute.LoadFromDB.route
    } else {
        NavigationRoute.LoginRegisterPage.route
    }

    NavHost(
        navController,
        startDestination = startingDestination
    ) {

        composable(NavigationRoute.Homepage.route) {
            BackHandler(true) {}
            AvailableVehiclesPage(navController)
            updateCurrentUser()
        }

        //Load before homepage is shown
        composable(NavigationRoute.LoadFromDB.route) {
            LoadFromDB(navController)
        }

        composable(NavigationRoute.Checkout.route){
            val tempCarModel =
                navController.previousBackStackEntry?.arguments?.getParcelable<TempVehicle>("tempVehicle")
            tempCarModel?.let {
                Checkout(vehicle = it, navController = navController)
            }
        }

        composable(NavigationRoute.CheckoutSuccess.route) {
            CheckoutSuccess(navController)
        }

        composable(NavigationRoute.CarInfo.route) {
            val carModel =
                navController.previousBackStackEntry?.arguments?.getParcelable<Vehicle>("vehicle")
            carModel?.let {
                CarInfo(vehicle = it, navController = navController)
            }
        }

        composable(NavigationRoute.ProfilePage.route) {
            ProfilePage(navController = navController)

        }

        // Authentication pages
        composable(NavigationRoute.LoginPage.route) {
            LoginPage(navController)
        }
        composable(NavigationRoute.RegisterPage.route) {
            RegisterPage(navController)
        }
        composable(NavigationRoute.LoginRegisterPage.route) {
            BackHandler(true) {}
            LoginRegisterPage(navController)
        }

        composable(NavigationRoute.HiwPage.route) {
            HiwPage(navController)
            updateHIW()
        }
        composable(NavigationRoute.HelpPage.route) {
            HelpPage(navController)
            updateHelp()
        }
        composable(NavigationRoute.ContactPage.route) {
            ContactPage(navController)
        }
    }
}






// Change page with argument: check last project