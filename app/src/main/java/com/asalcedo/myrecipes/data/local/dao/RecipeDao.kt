package com.asalcedo.myrecipes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asalcedo.myrecipes.data.local.entities.IngredientEntity
import com.asalcedo.myrecipes.data.local.entities.RecipeEntity
import com.asalcedo.myrecipes.data.local.entities.StepEntity

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.local.dao
 * Created by Alex Salcedo Silva on 25/2/24 at 14:33
 * All rights reserve 2022.
 ***/
@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<RecipeEntity>

    @Query("SELECT * FROM recipes WHERE name LIKE '%' || :recipeName || '%'")
    suspend fun searchRecipesByName(recipeName: String): List<RecipeEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecipes(recipes: List<RecipeEntity>)
    @Query("SELECT * FROM recipes")
    suspend fun getRecipes(): List<RecipeEntity>

    @Query("DELETE FROM recipes")
    suspend fun clearDatabase()

    //suspend fun saveRecipes(recipe: kotlin.collections.List<com.asalcedo.myrecipes.data.local.entities.RecipeEntity>): Long
    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveIngredients(ingredients: List<IngredientEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSteps(steps: List<StepEntity>)*/

    /*@Query("DELETE FROM steps")
    suspend fun deleteAllSteps()
    @Query("DELETE FROM ingredients")
    suspend fun deleteAllIngredients()
    @Query("DELETE FROM recipes")
    suspend fun deleteAllRecipes()*/

}