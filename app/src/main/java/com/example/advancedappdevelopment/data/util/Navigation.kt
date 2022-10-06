package com.example.advancedappdevelopment.data.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.ui.view.pages.CarInfo
import com.example.advancedappdevelopment.ui.view.pages.Checkout
import com.example.advancedappdevelopment.ui.view.pages.Homepage


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationRoute.Homepage.route
    /*if (Firebase.auth.currentUser != null)
        NavigationRoute.LoadFromDB.route
    else
        "authenticationOption"*/
    ) {
        /*
        // Login pages
        composable("authenticationOption") {
            AuthenticationView(
                register = remember(navController) { Action(navController) }.register,
                login = remember(navController) { Action(navController) }.login
            )
        }
        composable("register") {
            RegisterView(
                home = remember(navController) { Action(navController) }.home,
                back = remember(navController) { Action(navController) }.navigateBack
            )
        }
        composable("login") {
            LoginView(
                home = remember(navController) { Action(navController) }.home,
                back = remember(navController) { Action(navController) }.navigateBack
            )
        }
        */
        composable(NavigationRoute.Homepage.route) {
            Homepage(navController)
        }
        composable(NavigationRoute.CarInfo.route){
            CarInfo(navController)
        }
        composable(NavigationRoute.Checkout.route){
            Checkout(navController)
        }

    }
}
// Change page with argument - check last project