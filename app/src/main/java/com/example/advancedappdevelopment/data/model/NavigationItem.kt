package com.example.advancedappdevelopment.data.model


sealed class NavigationRoute(var route: String) {
    object Homepage : NavigationRoute("homepage")
    object CarInfo : NavigationRoute("carInfo")
    object Checkout : NavigationRoute("checkout")
}