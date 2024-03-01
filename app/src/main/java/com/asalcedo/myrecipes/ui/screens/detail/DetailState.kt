package com.asalcedo.myrecipes.ui.screens.detail

import com.asalcedo.myrecipes.domain.model.RecipeDomain

sealed class DetailState {
    data object Loading : DetailState()
    data class Success(val recipe: RecipeDomain) : DetailState()
    data class Error(val message: String) : DetailState()
}