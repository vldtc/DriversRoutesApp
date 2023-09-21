package com.example.cirlan.domain.navigation.screenroutes

sealed class ScreenRoutes(
    val route: String,
    var title: String
){
    object Main: ScreenRoutes(
        route = "main",
        title = "main"
    )
    object Routes: ScreenRoutes(
        route = "routes",
        title = "routes"
    )
}
