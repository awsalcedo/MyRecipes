package com.asalcedo.myrecipes.data.network.model

import com.asalcedo.myrecipes.data.local.entities.IngredientEntity
import com.asalcedo.myrecipes.domain.model.IngredientDomain
import com.google.gson.annotations.SerializedName

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.model
 * Created by Alex Salcedo Silva on 23/2/24 at 19:01
 * All rights reserve 2022.
 ***/
data class Ingredient(
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("unit")
    val unit: String
)

fun Ingredient.toDomain() = IngredientDomain(
    name = name,
    quantity = quantity,
    unit = unit
)

fun Ingredient.toEntity() = IngredientEntity(
    recipeId = 0,
    name = name,
    quantity = quantity,
    unit = unit
)
