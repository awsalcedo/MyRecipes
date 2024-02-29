package com.asalcedo.myrecipes.domain.usecase

import android.util.Log
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
    suspend operator fun invoke(): List<RecipeDomain> {
        return try {
            repository.getRecipesFromDatabase().takeIf { it.isNotEmpty() }
                ?: repository.getRecipesFromApi()?.also { it ->
                    repository.clearDatabase()
                    repository.insertRecipesDatabase(it.map { it.toEntity() })
                } ?: emptyList() // Si getRecipesFromApi() devuelve null, retorna una lista vacía
        } catch (e: Exception) {
            Log.e("GetRecipesUseCase", "Error getting recipes", e)
            emptyList() // Manejar el error retornando una lista vacía
        }
    }
}