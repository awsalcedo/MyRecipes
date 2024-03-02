package com.asalcedo.myrecipes.ui.screens.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asalcedo.myrecipes.domain.usecase.GetResponseApiKeyGoogle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val useCase: GetResponseApiKeyGoogle) : ViewModel() {
    private val _state = MutableStateFlow<MapState>(MapState.Initial)
    val state: StateFlow<MapState> = _state

    init {
        verifyApiKey()
    }

    fun setMapLocation(latitude: Float, longitude: Float) {
        try {
            if (latitude == 0f && longitude == 0f) {
                _state.value = MapState.Error("Invalid coordinates")
            } else {
                _state.value = MapState.Location(latitude, longitude)
            }
        } catch (e: Exception) {
            _state.value = MapState.Error("An error occurred: ${e.message}")
        }
    }

    private fun verifyApiKey() {
        try {
            viewModelScope.launch {
                val result = useCase()
                if (result == "The provided API key is invalid. ") {
                    _state.value = MapState.Error("Invalid API key")
                }
            }
        } catch (e: Exception) {
            _state.value = MapState.Error("An error occurred: ${e.message}")
        }
    }

}