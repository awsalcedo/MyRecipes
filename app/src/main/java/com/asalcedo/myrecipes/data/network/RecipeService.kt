package com.asalcedo.myrecipes.data.network

import com.asalcedo.myrecipes.BuildConfig
import com.asalcedo.myrecipes.data.network.model.GeocodeResponse
import com.asalcedo.myrecipes.data.network.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.model
 * Created by Alex Salcedo Silva on 23/2/24 at 19:24
 * All rights reserve 2022.
 ***/
interface RecipeService {
    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>

    @GET("${BuildConfig.BASE_GOOGLE_URL}geocode/json")
    suspend fun getGeocode(
        @Query("address") address: String = "address=1600+Amphitheatre+Parkway,+Mountain+View,+CA",
        @Query("key") apiKey: String
    ): GeocodeResponse
}