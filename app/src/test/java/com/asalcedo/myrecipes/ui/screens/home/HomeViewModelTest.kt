package com.asalcedo.myrecipes.ui.screens.home

import com.asalcedo.myrecipes.domain.model.RecipeDomain
import com.asalcedo.myrecipes.domain.usecase.GetRecipesUseCase
import com.asalcedo.myrecipes.testrules.CoroutinesTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @RelaxedMockK
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val rule = CoroutinesTestRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(getRecipesUseCase)
    }

    @Test
    fun `when UI is ready, then call get recipes`() = runTest {
        // Given
        val expectedRecipes = listOf(
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

        coEvery { getRecipesUseCase() } returns expectedRecipes

        // When
        viewModel.getRecipes()

        // Then
        //Se avanza el estado del dispatcher de corutinas hasta que no haya más corrutinas pendientes para que la corrutina en el viewModel (getRecipes()) se complete.
        advanceUntilIdle()

        runBlocking { assertEquals(HomeState.Success(expectedRecipes), viewModel.state.value) }

    }
}