package com.asalcedo.myrecipes.data

import android.util.Log
import com.asalcedo.myrecipes.data.network.RecipeService
import com.asalcedo.myrecipes.data.network.model.toDomain
import com.asalcedo.myrecipes.domain.RecipeRepository
import com.asalcedo.myrecipes.domain.model.RecipeDomain
import javax.inject.Inject

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.network
 * Created by Alex Salcedo Silva on 23/2/24 at 20:27
 * All rights reserve 2022.
 ***/
class RecipeRepositoryImpl @Inject constructor(
    private val apiService: RecipeService
) :
    RecipeRepository {
    override suspend fun getRecipes(): List<RecipeDomain>? {
        runCatching { apiService.getRecipes() }
            .onSuccess { recipeList ->
                return recipeList.map { recipe -> recipe.toDomain() }
            }
            .onFailure { Log.i("MyRecipesApp", "An error has ocurred: ${it.message}") }
        return null
    }
}