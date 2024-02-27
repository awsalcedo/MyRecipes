package com.asalcedo.myrecipes.ui.screens.detail

import com.asalcedo.myrecipes.domain.model.RecipeDomain

sealed class DetailState {
    data class Loading(val isLoading: Boolean = false) : DetailState()
    data class Success(val recipe: RecipeDomain) : DetailState()
    data class Error(val message: String) : DetailState()
}