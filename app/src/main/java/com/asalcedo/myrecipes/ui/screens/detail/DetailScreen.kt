package com.asalcedo.myrecipes.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.asalcedo.myrecipes.R
import com.asalcedo.myrecipes.domain.model.RecipeDomain

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.ui.screens.detail
 * Created by Alex Salcedo Silva on 25/2/24 at 22:30
 * All rights reserve 2022.
 ***/
@Composable
fun DetailScreen(navController: NavController, recipe: RecipeDomain) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(painter = painterResource(id = R.drawable.caesar_salad), contentDescription = null)
            Text(text = recipe.name)
            Text(text = recipe.description)


        }
    }

}