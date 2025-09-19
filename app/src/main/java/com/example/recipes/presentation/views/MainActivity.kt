package com.example.recipes.presentation.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.lifecycle.ViewModelProvider
import com.example.recipes.RecipeApp
import com.example.recipes.presentation.RecipeListViewModel
import com.example.recipes.presentation.ViewModelFactory
import com.example.recipes.ui.theme.RecipesTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RecipeListViewModel::class]
    }

    private val component by lazy {
        (application as RecipeApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            RecipesTheme {
                Scaffold { paddingValues ->
                    Recipes(paddingValues, viewModel)
                }

            }
        }
    }
}