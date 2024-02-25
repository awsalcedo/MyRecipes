package com.asalcedo.myrecipes.data.network.model

import com.asalcedo.myrecipes.domain.model.StepDomain
import com.google.gson.annotations.SerializedName

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.model
 * Created by Alex Salcedo Silva on 23/2/24 at 19:02
 * All rights reserve 2022.
 ***/
data class Step(
    @SerializedName("number")
    val number: Int,
    @SerializedName("description")
    val description: String
)

fun Step.toDomain() = StepDomain(
    number = number,
    description = description
)
