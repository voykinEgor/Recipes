package com.example.recipes.data

import com.example.recipes.BuildConfig
import com.example.recipes.data.entities.RecipesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/random?number=50")
    fun getRecipes(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): RecipesDto
}