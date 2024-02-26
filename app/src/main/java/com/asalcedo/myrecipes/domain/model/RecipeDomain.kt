package com.asalcedo.myrecipes.domain.model

import com.asalcedo.myrecipes.data.local.entities.RecipeEntity


/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.domain.model
 * Created by Alex Salcedo Silva on 23/2/24 at 19:56
 * All rights reserve 2022.
 ***/
data class RecipeDomain(
    val id: Long,
    val name: String,
    val description: String,
    val image: String,
    val ingredients: List<IngredientDomain>,
    val steps: List<StepDomain>,
    //val ingredients: String,
    //val steps: String,
    val originLatitude: Double,
    val originLongitude: Double
)

fun RecipeEntity.toDomain() = RecipeDomain(
    id = id,
    name = name,
    description = description,
    image = image,
    ingredients = ingredients.map { it.toDomain() },
    steps = steps.map { it.toDomain() },
    originLatitude = originLatitude,
    originLongitude = originLongitude
)
