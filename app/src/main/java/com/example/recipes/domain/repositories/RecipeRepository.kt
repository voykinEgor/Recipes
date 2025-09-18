package com.example.recipes.domain.repositories

import com.example.recipes.domain.entities.Recipe
import kotlinx.coroutines.flow.StateFlow

interface RecipeRepository {

    fun getRecipes(): StateFlow<List<Recipe>>

    fun getInfoAboutRecipe(recipeId: Int): StateFlow<Recipe>
}