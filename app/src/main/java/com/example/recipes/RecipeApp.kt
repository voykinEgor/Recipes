package com.example.recipes

import android.app.Application
import com.example.recipes.di.AppComponent
import com.example.recipes.di.DaggerAppComponent
import kotlin.getValue

class RecipeApp: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}