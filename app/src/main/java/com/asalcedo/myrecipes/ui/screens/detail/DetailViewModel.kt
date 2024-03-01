package com.asalcedo.myrecipes.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asalcedo.myrecipes.domain.usecase.GetRecipeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetRecipeByIdUseCase
) : ViewModel() {
    private var _state = MutableStateFlow<DetailState>(DetailState.Loading)
    val state: StateFlow<DetailState> = _state

    fun getRecipeById(recipeId: Long) {
        viewModelScope.launch {
            _state.value = DetailState.Loading
            try {
                val result = withContext(Dispatchers.IO) {
                    useCase(recipeId)
                }
                _state.value = DetailState.Success(result)
            } catch (e: Exception) {
                _state.value = DetailState.Error("An error occurred: ${e.message}")
            }
        }
    }
}