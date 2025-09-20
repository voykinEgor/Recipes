package com.example.recipes.data.mappers

import com.example.recipes.data.entities.RecipeDto
import com.example.recipes.data.entities.RecipeIngredientDto
import com.example.recipes.data.entities.RecipesDto
import com.example.recipes.domain.entities.Recipe
import com.example.recipes.domain.entities.RecipeIngredient
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
        likes = recipeDto.likes,
        summary = recipeDto.summary,
        extendedIngredients = mapIngredListDtoToDomain(recipeDto.extendedIngredients)
    )

    private fun mapIngredDtoToDomain(ingred: RecipeIngredientDto): RecipeIngredient = RecipeIngredient(
        id = ingred.id,
        name = ingred.name,
        image = ingred.image,
        original = ingred.original
    )

    fun mapIngredListDtoToDomain(ingredList: List<RecipeIngredientDto>): List<RecipeIngredient>{
        return ingredList.map { mapIngredDtoToDomain(it) }
    }
}