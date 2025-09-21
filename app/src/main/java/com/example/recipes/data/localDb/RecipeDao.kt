package com.example.recipes.data.localDb

import androidx.room.*
import com.example.recipes.data.localDb.entities.IngredientEntity
import com.example.recipes.data.localDb.entities.RecipeEntity
import com.example.recipes.data.localDb.entities.RecipeWithIngredients

@Dao
interface RecipeDao {
    @Transaction
    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<RecipeWithIngredients>

    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: Long): RecipeWithIngredients?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(ingredients: List<IngredientEntity>)

    @Query("DELETE FROM recipes")
    suspend fun clearRecipes()

    @Query("DELETE FROM ingredients")
    suspend fun clearIngredients()
}
