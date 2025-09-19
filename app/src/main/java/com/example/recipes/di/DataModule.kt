package com.example.recipes.di

import com.example.recipes.data.ApiFactory
import com.example.recipes.data.ApiService
import com.example.recipes.data.RecipeRepositoryImpl
import com.example.recipes.domain.repositories.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    companion object{
        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }
    }
}