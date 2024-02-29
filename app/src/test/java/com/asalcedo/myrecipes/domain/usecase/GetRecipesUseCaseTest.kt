package com.asalcedo.myrecipes.domain.usecase

import com.asalcedo.myrecipes.domain.RecipeRepository
import com.asalcedo.myrecipes.domain.model.RecipeDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

// Usando MockK
class GetRecipesUseCaseTest {

    @RelaxedMockK
    lateinit var repository: RecipeRepository

    private lateinit var useCase: GetRecipesUseCase

    private val expectedRecipes = listOf(
        RecipeDomain(
            id = 1,
            name = "Recipe 1",
            description = "Description 1",
            image = "image1.jpg",
            ingredients = emptyList(),
            steps = emptyList(),
            originLatitude = 0.0,
            originLongitude = 0.0
        ),
        RecipeDomain(
            id = 2,
            name = "Recipe 2",
            description = "Description 2",
            image = "image2.jpg",
            ingredients = emptyList(),
            steps = emptyList(),
            originLatitude = 0.0,
            originLongitude = 0.0
        )
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetRecipesUseCase(repository)
    }

    @Test
    fun `when the database return nothing then get values from api`() = runBlocking {
        coEvery { repository.getRecipesFromDatabase() } returns emptyList()
        coEvery { repository.getRecipesFromApi() } returns expectedRecipes
        val response = useCase.invoke()
        coVerify(exactly = 1) { repository.getRecipesFromApi() }
        assert(expectedRecipes == response)
    }

    @Test
    fun `when the database doesn't anything then get values from api`() = runBlocking {
        coEvery { repository.getRecipesFromDatabase() } returns emptyList()
        useCase.invoke()
        coVerify(exactly = 1) { repository.getRecipesFromApi() }
    }

    @Test
    fun `when the database return something then don't get values from api`() = runBlocking {
        coEvery { repository.getRecipesFromDatabase() } returns expectedRecipes
        val response = useCase.invoke()
        coVerify(exactly = 0) { repository.getRecipesFromApi() }
        assert(expectedRecipes == response)
    }

    @Test
    fun `when the api get values then insert values from database`() = runBlocking {
        coEvery { repository.getRecipesFromApi() } returns expectedRecipes
        val response = useCase.invoke()
        coVerify(exactly = 1) { repository.clearDatabase() }
        coVerify(exactly = 1) { repository.insertRecipesDatabase(any()) }
        assert(expectedRecipes == response)
    }


}