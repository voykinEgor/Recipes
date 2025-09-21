package com.example.recipes.presentation

import androidx.lifecycle.ViewModel
import com.example.recipes.domain.useCases.GetRecipesUseCase
import com.example.recipes.presentation.states.RecipeState
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(
    val getRecipesUseCase: GetRecipesUseCase
): ViewModel() {
    val recipesState = getRecipesUseCase()
        .onStart { RecipeState.Loading }
}