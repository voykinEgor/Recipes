package com.example.recipes.presentation.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipes.LOG_TAG
import com.example.recipes.R
import com.example.recipes.domain.entities.Recipe
import com.example.recipes.presentation.RecipeListViewModel
import com.example.recipes.presentation.ViewModelFactory
import com.example.recipes.presentation.navigation.NavGraph
import com.example.recipes.presentation.navigation.Screen
import com.example.recipes.presentation.states.RecipeState

@Composable
fun Recipes(
    paddingValues: PaddingValues,
    viewModel: RecipeListViewModel
) {
    val navController = rememberNavController()



    NavGraph(
        navHostController = navController,
        homeScreenContent = { ListRecipes(paddingValues, viewModel, navController) },
        recipeDetailsContent = {recipe ->
            RecipeDetails(recipe, paddingValues)
        }
    )
}

@Composable
fun ListRecipes(
    paddingValues: PaddingValues,
    viewModel: RecipeListViewModel,
    navController: NavHostController
) {
    var query by rememberSaveable { mutableStateOf("") }

    var showError by remember {
        mutableStateOf(false)
    }

    val recipesState by viewModel.recipesState.collectAsState(
        initial = RecipeState.Initial
    )

    var listRecipes = emptyList<Recipe>()

    when (recipesState) {
        is RecipeState.Error -> {
            showError = true
        }

        is RecipeState.Success -> {
            listRecipes = (recipesState as RecipeState.Success).recipes
            Log.d(LOG_TAG, (recipesState as RecipeState.Success).recipes.toString())
        }

        RecipeState.Initial -> {}
        RecipeState.Loading -> {}

    }
    if (showError) {
        ErrorDialog {
            showError = false
        }
    }

    val filteredRecipes = listRecipes.filter { recipe ->
        recipe.title.contains(query, ignoreCase = true)
    }

    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        item {


            Text(
                text = buildAnnotatedString {
                    append("Hello ")
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(R.color.bearch)
                        )
                    ) {
                        append("Foodie!")
                    }
                },
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, top = 30.dp)
            )
            Text(
                text = "Which Food Would You Like To Cook?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 20.dp)
            )

            SearchBar(query, {query = it})

            Text(
                text = "Popular Recipes",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
            )
        }
        items(filteredRecipes) {
            RecipeCard(
                recipe = it,
                onClick = {
                    navController.navigate(Screen.About.getRoute(it))
                }
            )
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text("Search Recipe") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search")
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
