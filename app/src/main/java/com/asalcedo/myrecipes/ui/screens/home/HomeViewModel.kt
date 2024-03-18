package com.asalcedo.myrecipes.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asalcedo.myrecipes.domain.model.RecipeDomain
import com.asalcedo.myrecipes.domain.usecase.GetRecipesUseCase
import com.asalcedo.myrecipes.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.ui.screens.home
 * Created by Alex Salcedo Silva on 23/2/24 at 20:43
 * All rights reserve 2022.
 ***/
@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: GetRecipesUseCase) : ViewModel() {
    private var _state = MutableStateFlow<RequestState<List<RecipeDomain>>>(RequestState.Idle)
    val state: StateFlow<RequestState<List<RecipeDomain>>> = _state

    init {
        getRecipes()
    }

    fun getRecipes() {
        viewModelScope.launch {
            useCase().collect { requestState ->
                _state.value = requestState
            }
        }
    }

    fun retryGetRecipes() {
        getRecipes()
    }
}