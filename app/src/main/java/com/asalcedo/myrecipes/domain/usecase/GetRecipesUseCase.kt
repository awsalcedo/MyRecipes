package com.asalcedo.myrecipes.domain.usecase

import com.asalcedo.myrecipes.domain.RecipeRepository
import javax.inject.Inject

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.domain.usecase
 * Created by Alex Salcedo Silva on 23/2/24 at 20:41
 * All rights reserve 2022.
 ***/
class GetRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke() = repository.getRecipes()
}