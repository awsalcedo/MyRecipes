package com.asalcedo.myrecipes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asalcedo.myrecipes.data.local.dao.RecipeDao
import com.asalcedo.myrecipes.data.local.entities.IngredientEntity
import com.asalcedo.myrecipes.data.local.entities.RecipeEntity
import com.asalcedo.myrecipes.data.local.entities.StepEntity

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.local
 * Created by Alex Salcedo Silva on 25/2/24 at 14:29
 * All rights reserve 2022.
 ***/
@Database(entities = [RecipeEntity::class, IngredientEntity::class, StepEntity::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao
}