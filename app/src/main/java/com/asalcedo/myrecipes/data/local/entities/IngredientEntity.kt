package com.asalcedo.myrecipes.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asalcedo.myrecipes.domain.model.IngredientDomain

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.local.entities
 * Created by Alex Salcedo Silva on 25/2/24 at 14:39
 * All rights reserve 2022.
 ***/
@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val quantity: String,
    val unit: String
)

fun IngredientDomain.toEntity() = IngredientEntity(
    name = name,
    quantity = quantity,
    unit = unit
)
