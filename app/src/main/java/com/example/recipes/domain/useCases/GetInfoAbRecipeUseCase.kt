package com.example.recipes.domain.useCases

import com.example.recipes.domain.repositories.RecipeRepository
import javax.inject.Inject

class GetInfoAbRecipeUseCase @Inject constructor(
    val repository: RecipeRepository
) {
    operator fun invoke(recipeId: Int) = repository.getInfoAboutRecipe(recipeId)
}