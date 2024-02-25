package com.asalcedo.myrecipes.domain.model

import androidx.annotation.DrawableRes


/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.domain.model
 * Created by Alex Salcedo Silva on 23/2/24 at 19:56
 * All rights reserve 2022.
 ***/
data class RecipeDomain(
    val id: Int,
    val name: String,
    val description: String,
    val imageResId: String,
    val ingredients: List<IngredientDomain>,
    val steps: List<StepDomain>,
    val originLatitude: Double,
    val originLongitude: Double
)
