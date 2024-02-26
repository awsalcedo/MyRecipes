package com.asalcedo.myrecipes.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.local.entities
 * Created by Alex Salcedo Silva on 25/2/24 at 14:41
 * All rights reserve 2022.
 ***/
@Entity(
    tableName = "steps",
    foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = ["id"],
        childColumns = ["recipeId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["recipeId"])]
)
data class StepEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val recipeId: Long,
    val number: Int,
    val description: String
)
