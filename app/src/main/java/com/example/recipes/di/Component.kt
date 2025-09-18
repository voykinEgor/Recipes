package com.example.recipes.di

import com.example.recipes.RecipeApp
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface Component {
    fun inject(app: RecipeApp)
}