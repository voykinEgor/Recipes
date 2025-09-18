package com.example.recipes

import android.app.Application
import com.example.recipes.di.Component
import dagger.android.DaggerApplication
import kotlin.getValue

class RecipeApp: Application() {

    val component: Component by lazy {
        TODO()
    }
}