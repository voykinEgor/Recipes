package com.example.recipes.data.mappers

import com.example.recipes.data.entities.RecipeDto
import com.example.recipes.data.entities.RecipesDto
import com.example.recipes.domain.entities.Recipe
import javax.inject.Inject

class RecipeMapper @Inject constructor(){

    fun mapRecipesDtoToDomain(recipesDto: RecipesDto): List<Recipe>{
        return recipesDto.recipeDtos.map { mapRecipeDtoToDomain(it) };
    }

    fun mapRecipeDtoToDomain(recipeDto: RecipeDto): Recipe = Recipe(
        id = recipeDto.id,
        title = recipeDto.title,
        image = recipeDto.image,
        readyInMinutes = recipeDto.readyInMinutes,
        servings = recipeDto.servings,
        isVegetarian = recipeDto.isVegetarian,
        isVegan = recipeDto.isVegan,
        isGlutenFree = recipeDto.isGlutenFree,
        likes = recipeDto.likes
    )
}