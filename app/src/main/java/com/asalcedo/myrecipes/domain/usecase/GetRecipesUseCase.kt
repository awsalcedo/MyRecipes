package com.asalcedo.myrecipes.domain.usecase

import com.asalcedo.myrecipes.data.local.entities.toEntity
import com.asalcedo.myrecipes.domain.RecipeRepository
import com.asalcedo.myrecipes.domain.model.RecipeDomain
import javax.inject.Inject

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.domain.usecase
 * Created by Alex Salcedo Silva on 23/2/24 at 20:41
 * All rights reserve 2022.
 ***/
class GetRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {
    /*suspend operator fun invoke() = repository.getRecipes()*/

    suspend operator fun invoke(): List<RecipeDomain> {
        val recipesDatabase = repository.getRecipesFromDatabase()
        return if (recipesDatabase.isEmpty()) {
            val recipes = repository.getRecipesFromApi()!!
            repository.clearDatabase()
            repository.insertRecipesDatabase(recipes.map { it.toEntity() })
            recipes
        } else {
            repository.getRecipesFromDatabase()
        }
    }
}