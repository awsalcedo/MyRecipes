package com.asalcedo.myrecipes.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asalcedo.myrecipes.domain.model.RecipeDomain

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.local.entities
 * Created by Alex Salcedo Silva on 25/2/24 at 14:37
 * All rights reserve 2022.
 ***/
@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val image: String,
    val ingredients: List<IngredientEntity>,
    val steps: List<StepEntity>,
    val originLatitude: Double,
    val originLongitude: Double
)

fun RecipeDomain.toEntity() = RecipeEntity(
    id = id,
    name = name,
    description = description,
    image = image,
    ingredients = ingredients.map { it.toEntity() },
    steps = steps.map { it.toEntity() },
    originLatitude = originLatitude,
    originLongitude = originLongitude
)