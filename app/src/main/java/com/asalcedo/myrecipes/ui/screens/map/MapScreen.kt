package com.asalcedo.myrecipes.ui.screens.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.asalcedo.myrecipes.ui.screens.components.MyCircularProgressIndicator
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(
    navController: NavController,
    latitude: Float,
    longitude: Float,
    recipeName: String
) {
    val viewModel: MapViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    // Actualiza el ViewModel con las coordenadas proporcionadas
    LaunchedEffect(key1 = latitude, key2 = longitude) {
        viewModel.setMapLocation(latitude, longitude)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Marcador de la Receta",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
        }
        when (val currentState = state) {
            is MapState.Initial -> {
                MyCircularProgressIndicator()
            }

            is MapState.Location -> {
                MapSettingsScreen(latitude, longitude, recipeName)
            }

            is MapState.Error -> {
                ErrorScreen(message = currentState.message)
            }
        }
    }

}

@Composable
fun MapSettingsScreen(latitude: Float, longitude: Float, recipeName: String) {
    val mapUiSettings by remember {
        mutableStateOf(MapUiSettings(compassEnabled = true))
    }

    val mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    val location = LatLng(latitude.toDouble(), longitude.toDouble())

    val markerPosition = rememberMarkerState(position = location)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }

    var showInfoWindow by remember { mutableStateOf(false) }

    val locationState = rememberMarkerState(position = markerPosition.position)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {


            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = mapProperties,
                uiSettings = mapUiSettings
            ) {
                Marker(
                    state = locationState,
                    title = "My Location Recipe",
                    snippet = recipeName,
                    onClick = {
                        if (showInfoWindow) {
                            locationState.showInfoWindow()
                        } else {
                            locationState.hideInfoWindow()
                        }
                        showInfoWindow = !showInfoWindow
                        return@Marker false
                    }
                )
            }

        }
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Snackbar(
            modifier = Modifier.padding(16.dp),
            contentColor = Color.White,
            content = {
                Text(message)
            }
        )
    }
}

