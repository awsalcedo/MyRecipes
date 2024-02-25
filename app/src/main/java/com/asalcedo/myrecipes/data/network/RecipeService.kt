package com.asalcedo.myrecipes.data.network

import com.asalcedo.myrecipes.data.network.model.Recipe
import retrofit2.http.GET

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.model
 * Created by Alex Salcedo Silva on 23/2/24 at 19:24
 * All rights reserve 2022.
 ***/
interface RecipeService {
    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>
}