package com.example.recipes.domain.repositories

import com.example.recipes.domain.entities.Recipe
import com.example.recipes.presentation.states.RecipeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RecipeRepository {
    fun getRecipes(): StateFlow<RecipeState>
}