package com.asalcedo.myrecipes.ui.screens.detail

import app.cash.turbine.test
import com.asalcedo.myrecipes.domain.model.RecipeDomain
import com.asalcedo.myrecipes.domain.usecase.GetRecipeByIdUseCase
import com.asalcedo.myrecipes.testrules.CoroutinesTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// Usando Turbine

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    // Aquí se inicializan las dependencias necesarias, como el caso de uso
    @RelaxedMockK
    private lateinit var useCase: GetRecipeByIdUseCase

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val rule = CoroutinesTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = DetailViewModel(useCase)
    }

    @Test
    fun `test getRecipeById success`() = runTest {
        // Define el valor esperado que devolverá el caso de uso
        val expectedRecipe = RecipeDomain(
            id = 1,
            name = "Test Recipe",
            description = "Test Description",
            image = "test_image.jpg",
            ingredients = emptyList(),
            steps = emptyList(),
            originLatitude = 0.0,
            originLongitude = 0.0
        )

        // Mockeo el comportamiento del caso de uso
        coEvery { useCase(any()) } returns expectedRecipe

        // Observo el estado del ViewModel usando Turbine
        viewModel.state.test {

            assertEquals(DetailState.Loading, awaitItem())
            // Act: Llamo al método getRecipeById del ViewModel
            viewModel.getRecipeById(1L)

            assertEquals(DetailState.Success(expectedRecipe), awaitItem())
        }
    }

    @Test
    fun `test getRecipeById error`() = runTest {
        // Define el error esperado que devolverá el caso de uso
        val expectedError = Exception("Test error message")

        // Mockeo el comportamiento del caso de uso para lanzar una excepción
        coEvery { useCase(any()) } throws expectedError

        // Observo el estado del ViewModel usando Turbine
        viewModel.state.test {
            // Act: Llamo al método getRecipeById del ViewModel
            viewModel.getRecipeById(1L)

            // Assert: Verifico que el estado cambie a Error con el mensaje de error esperado
            assertEquals(DetailState.Loading, awaitItem())
            assertEquals(
                DetailState.Error("An error occurred: ${expectedError.message}"),
                awaitItem()
            )
        }
    }
}