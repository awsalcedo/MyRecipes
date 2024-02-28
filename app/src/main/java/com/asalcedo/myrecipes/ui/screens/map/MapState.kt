package com.asalcedo.myrecipes.ui.screens.map

sealed class MapState {
    object Initial : MapState()
    data class Location(val latitude: Float, val longitude: Float) : MapState()
}