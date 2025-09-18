package com.example.recipes.data

import com.example.recipes.data.mappers.RecipeMapper
import com.example.recipes.domain.entities.Recipe
import com.example.recipes.domain.repositories.RecipeRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val mapper: RecipeMapper
): RecipeRepository {

    override fun getRecipes(): StateFlow<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override fun getInfoAboutRecipe(recipeId: Int): StateFlow<Recipe> {
        TODO("Not yet implemented")
    }
}