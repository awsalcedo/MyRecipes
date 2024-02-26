package com.asalcedo.myrecipes.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.findNavController
import com.asalcedo.myrecipes.R
import com.asalcedo.myrecipes.domain.model.RecipeDomain
import com.asalcedo.myrecipes.navigation.NavigationRoute

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


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Mostrar la lista de recetas filtradas
        when (val currentState = state) {
            is HomeState.Error -> ErrorScreen(currentState.message)
            HomeState.Loading -> MyCircularProgressIndicator()
            is HomeState.Success -> {
                RecipeList(recipes = currentState.recipes)
            }
        }
    }
}

@Composable
fun ErrorScreen(message: String) {
    // Aquí puedes mostrar un mensaje de error personalizado
}

@Composable
fun MyCircularProgressIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeList(recipes: List<RecipeDomain>) {
    val ctx = LocalContext.current
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    // Mostrar el SearchBar
    SearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = {
            Toast.makeText(ctx, query, Toast.LENGTH_SHORT).show()
            active = true
        },
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(8.dp),
        placeholder = { Text(text = "Search a recipe") },
    ){
        if (query.isNotEmpty()){
            val filterRecipes = recipes.filter { it.name.contains(query, ignoreCase = true) }

            LazyColumn {
                items(filterRecipes) { recipe ->
                    RecipeItem(recipe = recipe)
                }
            }
        } else {
            LazyColumn {
                items(recipes) { recipe ->
                    RecipeItem(recipe = recipe)
                }
            }
        }
    }
    LazyColumn {
        items(recipes) { recipe ->
            RecipeItem(recipe = recipe)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeItem(recipe: RecipeDomain, modifier: Modifier = Modifier) {
    val imageId = getImageId(recipe.image)
    val ctxs = LocalContext.current
    val detailRecipeNavController = rememberNavController()

    Card(
        modifier = modifier
            .clickable { }
            .padding(8.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            Toast.makeText(ctxs, recipe.name, Toast.LENGTH_SHORT).show()
            //detailRecipeNavController.navigate(NavigationRoute.Detail().route)
            //findNavController(ctxs).navigate("recipe/${recipe.id}")
        },
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.Black
            )
        }
    }
}

fun getImageId(image: String): Int {
    return when (image) {
        "spaghetti_carbonara" -> R.drawable.spaghetti_carbonara
        "chicken_tikka_masala" -> R.drawable.chicken_tikka_masala
        "margherita_pizza" -> R.drawable.margherita_pizza
        "caesar_salad" -> R.drawable.caesar_salad
        "chocolate_chip_cookies" -> R.drawable.chocolate_chip_cookies
        "guacamole" -> R.drawable.guacamole
        "sushi_rolls" -> R.drawable.sushi_rolls
        "beef_tacos" -> R.drawable.beef_tacos
        "beef_stir_fry" -> R.drawable.beef_stir_fry
        "chocolate_cake" -> R.drawable.chocolate_cake
        else -> R.drawable.spaghetti_carbonara
    }
}
