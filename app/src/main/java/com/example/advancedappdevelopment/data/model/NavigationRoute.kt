package com.example.advancedappdevelopment.data.model


sealed class NavigationRoute(var route: String) {
    object LoadFromDB            : NavigationRoute("loadFromDB")
    object Homepage              : NavigationRoute("homepage")
    object CarInfo               : NavigationRoute("carInfo")
    object LoginRegisterPage     : NavigationRoute("loginRegisterPage")
    object RegisterPage          : NavigationRoute("registerPage")
    object LoginPage             : NavigationRoute("loginPage")
}

/*
    ^^ String is only for description.
    Though you can also do:
        navController.navigate("homepage")
    to navigate to the homepage.

    But please do:
        navController.navigate(NavigationRoute.Homepage.route)
    instead
 */