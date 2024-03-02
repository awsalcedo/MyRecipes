package com.asalcedo.myrecipes.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/****
 * Project: MyRecipes
 * From: com.asalcedo.myrecipes.ui.screens.components
 * Created by Alex Salcedo Silva on 1/3/24 at 20:55
 * All rights reserve 2022.
 ***/

@Composable
fun MyCircularProgressIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}