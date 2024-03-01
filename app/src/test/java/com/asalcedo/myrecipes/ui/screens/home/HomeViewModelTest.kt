package com.asalcedo.myrecipes.ui.screens.home

import com.asalcedo.myrecipes.domain.model.RecipeDomain
import com.asalcedo.myrecipes.domain.usecase.GetRecipesUseCase
import com.asalcedo.myrecipes.testrules.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `when UI is ready, then call get recipes`() = runTest {
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

        val getRecipesUseCase: GetRecipesUseCase = mockk()
        coEvery { getRecipesUseCase.invoke() } returns expectedRecipes

        val viewModel = HomeViewModel(getRecipesUseCase)

        viewModel.getRecipes()


        /*delay(2000)
        advanceTimeBy(500)*/

        advanceUntilIdle()

        assertEquals(HomeState.Loading, viewModel.state.value)

        //advanceTimeBy(1600)




        assertEquals(HomeState.Success(expectedRecipes), viewModel.state.value)
    }


}