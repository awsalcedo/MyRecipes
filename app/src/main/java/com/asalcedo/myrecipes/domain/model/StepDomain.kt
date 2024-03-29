package com.asalcedo.myrecipes.domain.model

import com.asalcedo.myrecipes.data.local.entities.StepEntity

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.domain.model
 * Created by Alex Salcedo Silva on 23/2/24 at 20:00
 * All rights reserve 2022.
 ***/
data class StepDomain(
    val number: Int,
    val description: String
)

fun StepEntity.toDomain() = StepDomain(number, description)
