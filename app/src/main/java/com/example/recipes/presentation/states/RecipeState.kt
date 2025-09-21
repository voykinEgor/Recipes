package com.example.recipes.presentation.states

import com.example.recipes.domain.entities.Recipe

sealed class RecipeState {
    object Initial: RecipeState()
    object Loading: RecipeState()
    data class Success(val recipes: List<Recipe>): RecipeState()
    data class Error(val error: String): RecipeState()
    object Empty: RecipeState()
}