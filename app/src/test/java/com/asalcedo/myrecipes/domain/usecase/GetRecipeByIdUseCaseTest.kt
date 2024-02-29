package com.asalcedo.myrecipes.domain.usecase

import com.asalcedo.myrecipes.domain.RecipeRepository
import com.asalcedo.myrecipes.domain.model.RecipeDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

// Usando MockK
class GetRecipeByIdUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: RecipeRepository

    private lateinit var useCase: GetRecipeByIdUseCase

    @Before
    fun onBefore() {
        //Initial library of mock
        MockKAnnotations.init(this)
        useCase = GetRecipeByIdUseCase(repository)
    }

    @Test
    fun `when invoke is called, then return a recipe by id from database`() {
        // Arrange
        val recipeId = 1L
        val recipe = RecipeDomain(
            id = 1,
            name = "Recipe 1",
            description = "Description 1",
            image = "image1.jpg",
            ingredients = emptyList(),
            steps = emptyList(),
            originLatitude = 0.0,
            originLongitude = 0.0
        )
        coEvery { repository.getRecipeById(recipeId) } returns recipe

        // Act
        val result = runBlocking { useCase(recipeId) }

        // Assert
        assertEquals(recipe, result)
    }
}