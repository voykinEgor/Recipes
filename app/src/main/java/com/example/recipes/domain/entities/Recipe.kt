package com.example.recipes.domain.entities

data class Recipe(
    val id: Long,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val likes: Int,
    val isVegetarian: Boolean,
    val isVegan: Boolean,
    val isGlutenFree: Boolean
)