package com.asalcedo.myrecipes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.asalcedo.myrecipes.ui.screens.home.HomeScreen


/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.navigation
 * Created by Alex Salcedo Silva on 23/2/24 at 22:41
 * All rights reserve 2022.
 ***/

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute
) {
    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Home.route){
            HomeScreen()
        }
    }


}