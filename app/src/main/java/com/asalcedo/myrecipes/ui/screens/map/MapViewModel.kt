package com.asalcedo.myrecipes.ui.screens.map

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _state = MutableStateFlow<MapState>(MapState.Initial)
    val state: StateFlow<MapState> = _state

    init {
        val latitude = savedStateHandle.get<Float>("latitude")
        val longitude = savedStateHandle.get<Float>("longitude")
        if (latitude != null && longitude != null) {
            _state.value = MapState.Location(latitude, longitude)
        }
    }

    fun setMapLocation(latitude: Float, longitude: Float) {
        _state.value = MapState.Location(latitude, longitude)
    }
}