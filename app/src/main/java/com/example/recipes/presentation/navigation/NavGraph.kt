package com.example.recipes.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipes.domain.entities.Recipe
import com.example.recipes.presentation.views.RecipeDetails
import com.example.recipes.presentation.views.Recipes

@Composable
fun NavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    recipeDetailsContent: @Composable (Recipe) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(Screen.Home.route) {
            homeScreenContent()
        }
        composable(
            route = Screen.About.route,
            arguments = listOf(
                navArgument(Screen.RECIPE){
                    type = Recipe.RecipeNavigationType
                }
            )
        ) {
            val recipe = it.arguments?.getParcelable<Recipe>(Screen.RECIPE) ?: throw RuntimeException("Args in RecipeDetails is null")
            recipeDetailsContent(recipe)
        }
    }}