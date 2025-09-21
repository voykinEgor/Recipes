package com.example.recipes.di

import android.app.Application
import android.content.Context
import com.example.recipes.presentation.views.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(app: MainActivity)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}