package com.asalcedo.myrecipes.domain

import com.asalcedo.myrecipes.data.local.entities.RecipeWithIngredientsAndSteps
import com.asalcedo.myrecipes.domain.model.RecipeDomain

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.domain
 * Created by Alex Salcedo Silva on 23/2/24 at 19:52
 * All rights reserve 2022.
 ***/
interface RecipeRepository {
    suspend fun getRecipes(): List<RecipeDomain>?

    suspend fun getRecipesFromDatabase(): List<RecipeDomain>


}