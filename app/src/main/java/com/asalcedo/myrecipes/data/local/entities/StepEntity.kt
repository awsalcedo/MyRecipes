package com.asalcedo.myrecipes.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.asalcedo.myrecipes.domain.model.StepDomain

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.local.entities
 * Created by Alex Salcedo Silva on 25/2/24 at 14:41
 * All rights reserve 2022.
 ***/
@Entity(tableName = "steps",)
data class StepEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val number: Int,
    val description: String
)

fun StepDomain.toEntity() = StepEntity(
    number = number,
    description = description
)