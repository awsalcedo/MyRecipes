package com.asalcedo.myrecipes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.asalcedo.myrecipes.ui.screens.detail.DetailScreen
import com.asalcedo.myrecipes.ui.screens.home.HomeScreen
import com.asalcedo.myrecipes.ui.screens.map.MapScreen


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
        composable(NavigationRoute.Home.route) {
            HomeScreen(navController = navHostController)
        }
        composable(
            NavigationRoute.Detail.route,
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.LongType
                }
            )
        ) {
            DetailScreen(
                navController = navHostController,
                recipeId = it.arguments?.getLong("recipeId") ?: 0
            )
        }
        composable(
            NavigationRoute.Map.route,
            arguments = listOf(
                navArgument("latitude") {
                    type = NavType.FloatType
                },
                navArgument("longitude") {
                    type = NavType.FloatType
                }
            )
        ) {
            MapScreen(
                navController = navHostController,
                latitude = it.arguments?.getFloat("latitude") ?: 0f,
                longitude = it.arguments?.getFloat("longitude") ?: 0f,
                recipeName = it.arguments?.getString("recipeName") ?: ""
            )
        }
    }


}