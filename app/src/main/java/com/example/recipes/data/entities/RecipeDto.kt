package com.example.recipes.data.entities

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int,
    @SerializedName("servings")
    val servings: Int,
    @SerializedName("aggregateLikes")
    val likes: Int,
    @SerializedName("vegetarian")
    val isVegetarian: Boolean,
    @SerializedName("vegan")
    val isVegan: Boolean,
    @SerializedName("glutenFree")
    val isGlutenFree: Boolean,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<RecipeIngredientDto>
)