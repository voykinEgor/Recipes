package com.example.recipes.data

import com.example.recipes.data.mappers.RecipeMapper
import com.example.recipes.domain.entities.Recipe
import com.example.recipes.domain.repositories.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val mapper: RecipeMapper
): RecipeRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun getRecipes(): StateFlow<List<Recipe>> = flow{
        val response = apiService.getRecipes()
        val listRecipes = mapper.mapRecipesDtoToDomain(response)
        emit(listRecipes)
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    override fun getInfoAboutRecipe(recipeId: Int): StateFlow<Recipe> {
        TODO("Not yet implemented")
    }
}