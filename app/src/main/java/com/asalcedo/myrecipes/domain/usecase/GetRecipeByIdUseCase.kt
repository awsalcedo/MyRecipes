package com.asalcedo.myrecipes.domain.usecase

import com.asalcedo.myrecipes.domain.RecipeRepository
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(recipeId: Long) = repository.getRecipeById(recipeId)
}