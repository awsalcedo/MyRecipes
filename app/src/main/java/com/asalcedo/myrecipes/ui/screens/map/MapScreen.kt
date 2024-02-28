package com.asalcedo.myrecipes.ui.screens.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapViewModel = hiltViewModel(),
    latitude: Float,
    longitude: Float
) {
    // Actualiza el ViewModel con las coordenadas proporcionadas
    LaunchedEffect(key1 = latitude, key2 = longitude) {
        viewModel.setMapLocation(latitude, longitude)
    }

    val mapUiSettings = remember {
        mutableStateOf(MapUiSettings(compassEnabled = true))
    }

    val mapProperties = remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    val location = LatLng(latitude.toDouble(), longitude.toDouble())

    // Crear el objeto LatLng con las coordenadas proporcionadas
    val markerPosition = rememberMarkerState(position = location)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 10f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = mapProperties.value,
        uiSettings = mapUiSettings.value
    ) {
        Marker(
            state = MarkerState(
                position = markerPosition.position
            ),
            title = "My Location",
            snippet = "My Location",
        )
    }


}

