package com.example.advancedappdevelopment.data.model


sealed class NavigationRoute(var route: String) {
    object LoadFromDB            : NavigationRoute("loadFromDB")
    object Homepage              : NavigationRoute("homepage")
    object CarInfo               : NavigationRoute("carInfo")
    object Checkout              : NavigationRoute("checkout")
    object CheckoutSuccess       : NavigationRoute("checkoutSuccess")
    object LoginRegisterPage     : NavigationRoute("loginRegisterPage")
    object RegisterPage          : NavigationRoute("registerPage")
    object LoginPage             : NavigationRoute("loginPage")
    object ProfilePage           : NavigationRoute("profilePage")
    object HiwPage               : NavigationRoute("hiwPage")
    object HelpPage               : NavigationRoute("helpPage")
    object ContactPage               : NavigationRoute("contactPage")
}