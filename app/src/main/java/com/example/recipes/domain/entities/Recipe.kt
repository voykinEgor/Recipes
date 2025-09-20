package com.example.recipes.domain.entities

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
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
): Parcelable{

    companion object{
        val RecipeNavigationType = object : NavType<Recipe>(false){
            override fun get(
                bundle: Bundle,
                key: String
            ): Recipe? {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    bundle.getParcelable(key, Recipe::class.java)
                } else {
                    bundle.getParcelable<Recipe>(key)
                }
            }

            override fun parseValue(value: String): Recipe {
                return Gson().fromJson(value, Recipe::class.java)
            }

            override fun put(
                bundle: Bundle,
                key: String,
                value: Recipe
            ) {
                bundle.putParcelable(key, value)
            }

        }
    }
}