package com.asalcedo.myrecipes.ui.screens.map

sealed class MapState {
    data object Initial : MapState()
    data class Location(val latitude: Float, val longitude: Float) : MapState()
    data class Error(val message: String) : MapState()
}