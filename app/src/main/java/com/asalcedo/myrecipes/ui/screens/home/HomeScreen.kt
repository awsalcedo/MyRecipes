package com.asalcedo.myrecipes.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asalcedo.myrecipes.domain.model.RecipeDomain

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.ui.screens.home
 * Created by Alex Salcedo Silva on 23/2/24 at 20:57
 * All rights reserve 2022.
 ***/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    when (state) {
        is HomeState.Error -> ErrorScreen((state as HomeState.Error).message)
        HomeState.Loading -> MyCircularProgressIndicator()
        is HomeState.Success -> RecipeList(recipes = (state as HomeState.Success).recipes)
    }


}

@Composable
fun ErrorScreen(message: String) {

}


@Composable
fun MyCircularProgressIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun RecipeList(recipes: List<RecipeDomain>) {
    LazyColumn{
        items(recipes){recipe ->
            RecipeItem(recipe = recipe)
        }
    }
}

@Composable
fun RecipeItem(recipe: RecipeDomain, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .clickable { }
            .padding(8.dp)
            .fillMaxWidth(),
        //elevation = CardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium,
        //backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            /*Image(
                painter = painterResource(id = recipe.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )*/
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black
            )
        }
    }
}
