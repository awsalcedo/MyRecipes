package com.asalcedo.myrecipes.data.local.converters

import androidx.room.TypeConverter
import com.asalcedo.myrecipes.data.local.entities.IngredientEntity
import com.asalcedo.myrecipes.data.local.entities.StepEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromIngredientList(ingredients: List<IngredientEntity>?): String {
        val gson = Gson()
        return gson.toJson(ingredients)
    }

    @TypeConverter
    fun toIngredientList(ingredientsString: String): List<IngredientEntity> {
        val gson = Gson()
        val listType = object : TypeToken<List<IngredientEntity>>() {}.type
        return gson.fromJson(ingredientsString, listType)
    }

    @TypeConverter
    fun fromStepList(steps: List<StepEntity>?): String {
        val gson = Gson()
        return gson.toJson(steps)
    }

    @TypeConverter
    fun toStepList(stepsString: String): List<StepEntity> {
        val gson = Gson()
        val listType = object : TypeToken<List<StepEntity>>() {}.type
        return gson.fromJson(stepsString, listType)
    }
}