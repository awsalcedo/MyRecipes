package com.asalcedo.myrecipes.ui.screens.home

import com.asalcedo.myrecipes.domain.model.RecipeDomain

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.ui.screens.home
 * Created by Alex Salcedo Silva on 23/2/24 at 20:47
 * All rights reserve 2022.
 ***/
sealed class HomeState {
    data object Loading : HomeState()
    data class Success(val recipes: List<RecipeDomain>) : HomeState()
    data class Error(val message: String) : HomeState()
}