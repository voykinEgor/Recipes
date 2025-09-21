package com.example.recipes.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.recipes.data.serverDb.ApiFactory
import com.example.recipes.data.serverDb.ApiService
import com.example.recipes.data.RecipeRepositoryImpl
import com.example.recipes.data.localDb.AppDatabase
import com.example.recipes.data.localDb.RecipeDao
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

        @Provides
        @ApplicationScope
        fun provideDatabase(context: Context): AppDatabase {
            return AppDatabase.getInstance(context)
        }

        @Provides
        @ApplicationScope
        fun provideRecipeDao(db: AppDatabase): RecipeDao = db.recipeDao()
    }
}