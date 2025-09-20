package com.example.recipes.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeIngredient(
    val id: Long,
    val name: String,
    val image: String?,
    val original: String
) : Parcelable

