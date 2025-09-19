package com.example.recipes.di

import androidx.lifecycle.ViewModel
import com.example.recipes.presentation.RecipeListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecipeListViewModel::class)
    fun bindRecipeListViewModel(impl: RecipeListViewModel): ViewModel
}