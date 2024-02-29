package com.asalcedo.myrecipes.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asalcedo.myrecipes.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.ui.screens.home
 * Created by Alex Salcedo Silva on 23/2/24 at 20:43
 * All rights reserve 2022.
 ***/
@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: GetRecipesUseCase) : ViewModel() {
    private var _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            try {
                val result = withContext(Dispatchers.IO) {
                    useCase()
                }
                if (result.isNotEmpty()) {
                    _state.value = HomeState.Success(result)
                } else {
                    _state.value =
                        HomeState.Error("Failed to load recipes. Please try again later.")
                }
            } catch (e: Exception) {
                _state.value = HomeState.Error("An unexpected error occurred.")
                Log.e("HomeViewModel", "Unexpected error", e)
            }
        }
    }

    fun retryGetRecipes() {
        getRecipes()
    }
}