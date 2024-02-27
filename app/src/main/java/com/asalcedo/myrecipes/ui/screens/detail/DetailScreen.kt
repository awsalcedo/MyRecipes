package com.asalcedo.myrecipes.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            when (val currentState = state) {
                is DetailState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is DetailState.Error -> {
                    Text(text = currentState.message)
                }

                is DetailState.Success -> {
                    DetailContent(recipe = currentState.recipe, navController = navController)
                    //RecipeDetailScreen(recipe = currentState.recipe, navController = navController)
                }
            }
        }
    }

}

@Composable
fun DetailContent(recipe: RecipeDomain, navController: NavController) {

    Column {
        val imageId = getImageId(recipe.image)
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(text = recipe.name)
        Text(text = recipe.description)
    }

}

@Composable
fun RecipeDetailScreen(recipe: RecipeDomain, navController: NavController) {
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
            SpacerItem(16)
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
            SpacerItem(16)
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
            SpacerItem(16)
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
            SpacerItem(16)
        }

        item {
            Button(
                onClick = { /* Perform some action */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save Recipe")
            }
        }
    }
}

@Composable
private fun SpacerItem(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}

