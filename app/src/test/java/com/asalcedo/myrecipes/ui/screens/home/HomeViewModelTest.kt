package com.asalcedo.myrecipes.ui.screens.home

import com.asalcedo.myrecipes.domain.model.RecipeDomain
import com.asalcedo.myrecipes.domain.usecase.GetRecipesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @RelaxedMockK
    private lateinit var useCase: GetRecipesUseCase

    private lateinit var viewModel: HomeViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(useCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    /*@get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()*/

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all recipes and set on the state`() =
        runTest {
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

            coEvery { useCase.invoke() } returns expectedRecipes
            assert(viewModel.state.value == HomeState.Success(expectedRecipes))
        }


}