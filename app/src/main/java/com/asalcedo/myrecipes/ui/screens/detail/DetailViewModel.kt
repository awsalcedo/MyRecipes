package com.asalcedo.myrecipes.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
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
    savedStateHandle: SavedStateHandle,
    private val useCase: GetRecipeByIdUseCase
) : ViewModel() {
    private var _state = MutableStateFlow<DetailState>(DetailState.Loading())
    val state: StateFlow<DetailState> = _state

    init {
        val recipeId = savedStateHandle.get<Long?>("recipeId")
        if (recipeId != null) {
            getRecipeById(recipeId)
        }
    }

    private fun getRecipeById(recipeId: Long) {
        viewModelScope.launch {
            _state.value = DetailState.Loading(true)
            val result = withContext(Dispatchers.IO) {
                useCase(recipeId)
            }
            if (result != null) {
                _state.value = DetailState.Success(result)
            } else {
                _state.value = DetailState.Error("An error ocurred, try again later !!!")
            }
        }

    }
}