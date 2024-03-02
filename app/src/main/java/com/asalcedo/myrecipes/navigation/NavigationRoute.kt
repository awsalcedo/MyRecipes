package com.asalcedo.myrecipes.navigation

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.navigation
 * Created by Alex Salcedo Silva on 23/2/24 at 22:39
 * All rights reserve 2022.
 ***/
sealed class NavigationRoute(val route: String) {
    object Home : NavigationRoute("home")
    object Detail : NavigationRoute("detail/{recipeId}") {
        fun buildRoute(recipeId: Long): String {
            return "detail/$recipeId"
        }
    }
    object Map : NavigationRoute("map/{latitude}/{longitude}/{recipeName}") {
        fun buildRoute(latitude: Float, longitude: Float, recipeName: String): String {
            return "map/$latitude/$longitude/$recipeName"
        }
    }
}