package com.asalcedo.myrecipes.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.asalcedo.myrecipes.data.local.entities.RecipeEntity

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.local.dao
 * Created by Alex Salcedo Silva on 25/2/24 at 14:33
 * All rights reserve 2022.
 ***/
@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes WHERE name LIKE '%' || :recipeName || '%'")
    fun searchRecipesByName(recipeName: String): List<RecipeEntity>
}