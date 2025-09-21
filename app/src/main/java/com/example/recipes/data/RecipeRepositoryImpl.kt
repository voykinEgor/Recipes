package com.example.recipes.data

import com.example.recipes.data.localDb.RecipeDao
import com.example.recipes.data.localDb.entities.IngredientEntity
import com.example.recipes.data.localDb.entities.RecipeEntity
import com.example.recipes.data.mappers.RecipeMapper
import com.example.recipes.data.serverDb.ApiService
import com.example.recipes.domain.entities.Recipe
import com.example.recipes.domain.repositories.RecipeRepository
import com.example.recipes.presentation.states.RecipeState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val mapper: RecipeMapper,
    val recipeDao: RecipeDao
): RecipeRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun getRecipes(): StateFlow<RecipeState> = flow {
        try {
            val response = apiService.getRecipes()
            val recipes = mapper.mapRecipesDtoToDomain(response)

            recipeDao.clearRecipes()
            recipeDao.clearIngredients()
            val recipeEntities = response.recipeDtos.map { dto ->
                RecipeEntity(
                    id = dto.id,
                    title = dto.title,
                    image = dto.image,
                    readyInMinutes = dto.readyInMinutes,
                    servings = dto.servings,
                    likes = dto.likes,
                    isVegetarian = dto.isVegetarian,
                    isVegan = dto.isVegan,
                    isGlutenFree = dto.isGlutenFree,
                    summary = dto.summary
                )
            }
            recipeDao.insertRecipes(recipeEntities)

            val ingredientsEntities = response.recipeDtos.flatMap { dto ->
                dto.extendedIngredients.map { ing ->
                    IngredientEntity(
                        recipeId = dto.id,
                        name = ing.name,
                        image = ing.image,
                        original = ing.original
                    )
                }
            }
            recipeDao.insertIngredients(ingredientsEntities)

            emit(RecipeState.Success(recipes))
        } catch (e: Exception) {
            val cached = recipeDao.getAllRecipes()
            if (cached.isNotEmpty()){
                emit(RecipeState.Success(mapper.mapRecipesWithIngredientsListToDomain(cached)))
            }else{
                emit(RecipeState.Empty)
            }

        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = RecipeState.Loading
    )
}