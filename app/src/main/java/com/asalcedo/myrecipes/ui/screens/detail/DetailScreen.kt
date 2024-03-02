package com.asalcedo.myrecipes.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.asalcedo.myrecipes.domain.model.RecipeDomain
import com.asalcedo.myrecipes.navigation.NavigationRoute
import com.asalcedo.myrecipes.util.Utilities.getImageId

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.ui.screens.detail
 * Created by Alex Salcedo Silva on 25/2/24 at 22:30
 * All rights reserve 2022.
 ***/
@Composable
fun DetailScreen(
    navController: NavController,
    recipeId: Long,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    var originLatitude by remember { mutableFloatStateOf(0f) }
    var originLongitude by remember { mutableFloatStateOf(0f) }
    var recipeName by remember { mutableStateOf("") }

    LaunchedEffect(recipeId) {
        viewModel.getRecipeById(recipeId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
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
                    text = "Detalle de la Receta",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
            }

            when (val currentState = state) {
                is DetailState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is DetailState.Error -> {
                    Text(text = currentState.message)
                }

                is DetailState.Success -> {
                    originLatitude = currentState.recipe.originLatitude.toFloat()
                    originLongitude = currentState.recipe.originLongitude.toFloat()
                    recipeName = currentState.recipe.name
                    RecipeDetailScreen(recipe = currentState.recipe)
                }
            }
        }

        FloatingActionButton(
            onClick = {
                navController.navigate(
                    NavigationRoute.Map.buildRoute(
                        originLatitude,
                        originLongitude,
                        recipeName
                    )
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.Place, contentDescription = "Mapa")
        }
    }

}

@Composable
fun RecipeDetailScreen(recipe: RecipeDomain) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            val imageId = getImageId(recipe.image)
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            SpacerItem()
        }

        item {
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
            SpacerItem()
        }

        item {
            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
            recipe.ingredients.forEach { ingredient ->
                Text(
                    text = "${ingredient.quantity} ${ingredient.unit} ${ingredient.name}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            SpacerItem()
        }

        item {
            Text(
                text = "Steps",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
            recipe.steps.forEach { step ->
                Text(
                    text = "${step.number}. ${step.description}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            SpacerItem()
        }
    }
}

@Composable
private fun SpacerItem() {
    Spacer(modifier = Modifier.height(16.dp))
}

