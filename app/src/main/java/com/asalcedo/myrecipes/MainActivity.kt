package com.asalcedo.myrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.asalcedo.myrecipes.navigation.NavigationHost
import com.asalcedo.myrecipes.navigation.NavigationRoute
import com.asalcedo.myrecipes.ui.screens.home.HomeViewModel
import com.asalcedo.myrecipes.ui.theme.MyRecipesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationHost(navHostController = navController, startDestination = NavigationRoute.Home)
                }
            }
        }
    }
}
