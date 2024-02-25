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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asalcedo.myrecipes.R
import com.asalcedo.myrecipes.domain.model.RecipeDomain

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.ui.screens.home
 * Created by Alex Salcedo Silva on 23/2/24 at 20:57
 * All rights reserve 2022.
 ***/

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
    LazyColumn {
        items(recipes) { recipe ->
            RecipeItem(recipe = recipe)
        }
    }
}

@Composable
fun RecipeItem(recipe: RecipeDomain, modifier: Modifier = Modifier) {
    val imageId = getImageId(recipe.image)
    Card(
        modifier = modifier
            .clickable { }
            .padding(8.dp)
            .fillMaxWidth(),
        //elevation = CardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium,
        //backgroundColor = Color.White
    ) {
        Column{
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
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
