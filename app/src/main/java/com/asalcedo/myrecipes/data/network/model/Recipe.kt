package com.asalcedo.myrecipes.data.network.model

import com.asalcedo.myrecipes.domain.model.RecipeDomain
import com.google.gson.annotations.SerializedName

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.model
 * Created by Alex Salcedo Silva on 23/2/24 at 18:59
 * All rights reserve 2022.
 ***/
data class Recipe(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageResId")
    val imageResId: String,
    @SerializedName("ingredients")
    val ingredients: List<Ingredient>,
    @SerializedName("steps")
    val steps: List<Step>,
    @SerializedName("originLatitude")
    val originLatitude: Double,
    @SerializedName("originLongitude")
    val originLongitude: Double
)

fun Recipe.toDomain() = RecipeDomain(
    id = id,
    name = name,
    description = description,
    imageResId = imageResId,
    ingredients = ingredients.map { it.toDomain() },
    steps = steps.map { it.toDomain() },
    originLatitude = originLatitude,
    originLongitude = originLongitude
)
