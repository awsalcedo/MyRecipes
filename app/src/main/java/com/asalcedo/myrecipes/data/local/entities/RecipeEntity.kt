package com.asalcedo.myrecipes.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val originLatitude: Double,
    val originLongitude: Double
)
