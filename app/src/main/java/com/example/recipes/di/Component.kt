package com.example.recipes.di

import com.example.recipes.RecipeApp
import com.example.recipes.presentation.views.MainActivity
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface Component {
    fun inject(app: MainActivity)
}