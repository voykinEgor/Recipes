package com.example.recipes.presentation.navigation

import android.net.Uri
import com.example.recipes.domain.entities.Recipe
import com.google.gson.Gson

sealed class Screen(
    val route: String
){
    object Home: Screen(HOME)
    object About: Screen(ABOUT){
        private const val RECIPE_FOR_ARGS = "about_string"

        fun getRoute(recipe: Recipe): String{
            val recipeJson = Gson().toJson(recipe)
            return "$RECIPE_FOR_ARGS/${recipeJson.encode()}"
        }
    }

    companion object{
        const val RECIPE = "recipe"
        private const val HOME = "home_string"
        private const val ABOUT = "about_string/{$RECIPE}"
    }
}

private fun String.encode(): String{
    return Uri.encode(this)
}