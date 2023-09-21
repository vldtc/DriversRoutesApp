package com.example.cirlan.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cirlan.domain.navigation.screenroutes.ScreenRoutes
import com.example.cirlan.presentation.screens.main.MainScreen
import com.example.cirlan.presentation.screens.route.RouteScreen

@Composable
fun MainNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Main.route
    ){
        composable(ScreenRoutes.Main.route){
            MainScreen(
                onDriverClick = { driverId ->
                    navController.navigate("${ScreenRoutes.Routes.route}/$driverId")
                }
            )
        }
        composable("${ScreenRoutes.Routes.route}/{driverId}"){ backStackEntry ->
            RouteScreen(
                id = backStackEntry.arguments?.getString("driverId").toString(),
                onBackButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}