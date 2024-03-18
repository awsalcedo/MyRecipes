package com.asalcedo.myrecipes.domain.usecase

import android.util.Log
import com.asalcedo.myrecipes.data.local.entities.toEntity
import com.asalcedo.myrecipes.domain.RecipeRepository
import com.asalcedo.myrecipes.domain.model.RecipeDomain
import com.asalcedo.myrecipes.util.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.domain.usecase
 * Created by Alex Salcedo Silva on 23/2/24 at 20:41
 * All rights reserve 2022.
 ***/
class GetRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(): Flow<RequestState<List<RecipeDomain>>> {
        return flow {
            emit(RequestState.Loading)
            try {
                val recipes = repository.getRecipesFromDatabase()
                if (recipes.isNotEmpty()) {
                    emit(RequestState.Success(recipes))
                } else {
                    repository.getRecipesFromApi().collect { apiResult ->
                        when (apiResult) {
                            is RequestState.Success -> {
                                val apiRecipes = apiResult.data
                                repository.clearDatabase()
                                repository.insertRecipesDatabase(apiRecipes.map { it.toEntity() })
                                emit(RequestState.Success(apiRecipes))
                            }

                            is RequestState.Error -> {
                                emit(RequestState.Error(apiResult.message))
                            }

                            else -> {
                                emit(apiResult)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("GetRecipesUseCase", "Error getting recipes", e)
                emit(RequestState.Error("An unexpected error occurred"))
            }
        }
    }
}
