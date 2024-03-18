package com.asalcedo.myrecipes.data

import android.util.Log
import com.asalcedo.myrecipes.BuildConfig.GM_API_KEY
import com.asalcedo.myrecipes.data.local.dao.RecipeDao
import com.asalcedo.myrecipes.data.local.entities.RecipeEntity
import com.asalcedo.myrecipes.data.network.RecipeService
import com.asalcedo.myrecipes.data.network.model.toDomain
import com.asalcedo.myrecipes.domain.RecipeRepository
import com.asalcedo.myrecipes.domain.model.RecipeDomain
import com.asalcedo.myrecipes.domain.model.toDomain
import com.asalcedo.myrecipes.util.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    override suspend fun getRecipesFromApi(): Flow<RequestState<List<RecipeDomain>>> {
        return flow {
            emit(RequestState.Loading)
            try {
                val result = apiService.getRecipes().map { it.toDomain() }
                emit(RequestState.Success(data = result))
            } catch (e: Exception) {
                emit(RequestState.Error(e.message ?: "An unexpected error has occurred"))
            }
        }
    }

    override suspend fun insertRecipesDatabase(recipes: List<RecipeEntity>) {
        dao.saveRecipes(recipes)
    }

    override suspend fun getRecipeById(recipeId: Long): RecipeDomain {
        return dao.getRecipeById(recipeId).toDomain()
    }

    override suspend fun getRecipesFromDatabase(): List<RecipeDomain> {
        return dao.getRecipes().map { it.toDomain() }
    }

    override suspend fun clearDatabase() {
        dao.clearDatabase()
    }

    override suspend fun getGeocode(): String {
        return runCatching {
            val result = apiService.getGeocode(apiKey = GM_API_KEY)
            result.error_message
        }.onFailure {
            Log.i("RecipeRepositoryImpl", "An error has occurred getGeocode: ${it.message}")
            throw it
        }.getOrThrow()
    }
}