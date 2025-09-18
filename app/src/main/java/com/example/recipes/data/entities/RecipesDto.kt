package com.example.recipes.data.entities

import com.google.gson.annotations.SerializedName

data class RecipesDto(
    @SerializedName("recipes")
    val recipeDtos: List<RecipeDto>
)
