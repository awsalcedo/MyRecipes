package com.asalcedo.myrecipes.data

import android.util.Log
import com.asalcedo.myrecipes.data.local.dao.RecipeDao
import com.asalcedo.myrecipes.data.local.entities.IngredientEntity
import com.asalcedo.myrecipes.data.local.entities.StepEntity
import com.asalcedo.myrecipes.data.network.RecipeService
import com.asalcedo.myrecipes.data.network.model.Recipe
import com.asalcedo.myrecipes.data.network.model.toDomain
import com.asalcedo.myrecipes.data.network.model.toEntity
import com.asalcedo.myrecipes.domain.RecipeRepository
import com.asalcedo.myrecipes.domain.model.RecipeDomain
import com.asalcedo.myrecipes.domain.model.toDomain
import javax.inject.Inject

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.data.network
 * Created by Alex Salcedo Silva on 23/2/24 at 20:27
 * All rights reserve 2022.
 ***/
class RecipeRepositoryImpl @Inject constructor(
    private val apiService: RecipeService,
    private val dao: RecipeDao
) :
    RecipeRepository {
    override suspend fun getRecipes(): List<RecipeDomain>? {
        runCatching { apiService.getRecipes() }
            .onSuccess { recipeList ->
                saveRecipes(recipeList)
                return recipeList.map { recipe -> recipe.toDomain() }
            }
            .onFailure { Log.i("MyRecipesApp", "An error has ocurred: ${it.message}") }
        return null
    }

    private suspend fun saveRecipes(recipes: List<Recipe>) {
        dao.saveRecipes(recipes.map { it.toEntity() })
    }

    override suspend fun getRecipesFromDatabase(): List<RecipeDomain> {
        return dao.getRecipes().map { it.toDomain() }
    }

    /*private suspend fun saveRecipes(recipes: List<Recipe>) {
        for (recipe in recipes) {
            // Insertar la receta en la tabla RecipeEntity y obtener su ID
            val recipeId = dao.saveRecipes(recipe.toEntity())

            // Asignar el ID de la receta a cada ingrediente y paso
            val ingredientEntities = recipe.ingredients.map {
                IngredientEntity(
                    recipeId = recipeId,
                    name = it.name,
                    quantity = it.quantity,
                    unit = it.unit
                )
            }
            val stepEntities = recipe.steps.map {
                StepEntity(
                    recipeId = recipeId,
                    number = it.number,
                    description = it.description
                )
            }

            // Insertar los ingredientes y pasos asociados a la receta
            dao.saveIngredients(ingredientEntities)
            dao.saveSteps(stepEntities)
        }
    }*/


    /*suspend fun clearAllRecipes(){
        dao.deleteAllSteps()
        dao.deleteAllIngredients()
        dao.deleteAllRecipes()
    }*/
}