package com.asalcedo.myrecipes.domain.usecase

import com.asalcedo.myrecipes.domain.RecipeRepository
import javax.inject.Inject

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.domain.usecase
 * Created by Alex Salcedo Silva on 1/3/24 at 21:59
 * All rights reserve 2022.
 ***/
class GetResponseApiKeyGoogle @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(): String {
        return repository.getGeocode()
    }
}