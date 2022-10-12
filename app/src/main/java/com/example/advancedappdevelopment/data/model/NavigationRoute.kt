package com.example.advancedappdevelopment.data.model


sealed class NavigationRoute(var route: String) {
    object LoadFromDB   : NavigationRoute("loadFromDB")
    object Homepage     : NavigationRoute("homepage")
    object CarInfo      : NavigationRoute("carInfo")
    object Checkout     : NavigationRoute("checkout")
}