package com.asalcedo.myrecipes.domain.model

import com.asalcedo.myrecipes.data.local.entities.IngredientEntity

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.domain.model
 * Created by Alex Salcedo Silva on 23/2/24 at 19:58
 * All rights reserve 2022.
 ***/
data class IngredientDomain(
    val name: String,
    val quantity: String,
    val unit: String
)

fun IngredientEntity.toDomain() = IngredientDomain(
    name = name,
    quantity = quantity,
    unit = unit
)
