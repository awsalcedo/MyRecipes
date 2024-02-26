package com.asalcedo.myrecipes.navigation

import com.asalcedo.myrecipes.domain.model.RecipeDomain

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.navigation
 * Created by Alex Salcedo Silva on 23/2/24 at 22:39
 * All rights reserve 2022.
 ***/
sealed class NavigationRoute(val route: String) {
    object Home : NavigationRoute("home")
    data class Detail(val recipe: RecipeDomain) : NavigationRoute("detail/{recipe}")
}