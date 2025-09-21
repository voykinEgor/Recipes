package com.example.recipes.data.localDb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true) val ingredientId: Long = 0,
    val recipeId: Long,   // foreign key на рецепт
    val name: String,
    val image: String?,
    val original: String
)
