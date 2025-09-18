package com.example.recipes.presentation

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipes.LOG_TAG
import com.example.recipes.R
import com.example.recipes.domain.entities.Recipe
import com.example.recipes.ui.theme.RecipesTheme

@Composable
fun Recipes(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background)
    ) {
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

        //TODO: Вместо ываыва - State
        SearchBar("ываыва", {})

        Text(
            text = "Popular Recipes",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
        )

        val sampleRecipe = Recipe(
            id = 640591,
            title = "Tomato and Bacon Pizza With Rice Crust",
            image = "https://img.spoonacular.com/recipes/640591-556x370.jpg",
            readyInMinutes = 45,
            servings = 8,
            likes = 3,
            isVegetarian = false,
            isVegan = false,
            isGlutenFree = true
        )
        RecipeCard(sampleRecipe)

        LazyColumn(
            modifier = Modifier
        ) {

        }
    }
}

//@Preview
//@Composable
//fun DayThemeRecipes() {
//    RecipesTheme(darkTheme = false) {
//        Recipes()
//    }
//}

//@Preview
//@Composable
//fun NightThemeRecipes() {
//    RecipesTheme(darkTheme = true) {
//        Recipes()
//    }
//}

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
