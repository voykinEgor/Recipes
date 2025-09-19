package com.example.recipes.presentation.views

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.recipes.R
import com.example.recipes.domain.entities.Recipe


@Composable
fun RecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier
){
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            AsyncImage(
                model = recipe.image,
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = diet(recipe),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoItem(icon = R.drawable.ic_servings, text = "${recipe.servings} Servings")
                InfoItem(icon = R.drawable.ic_time, text = "${recipe.readyInMinutes} Minutes")
                InfoItem(icon = R.drawable.ic_like, text = "$${recipe.likes} Likes")
            }
        }
    }
}

@Composable
fun InfoItem(icon: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified, // сохраняем цвет из svg/png
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, style = MaterialTheme.typography.bodySmall)
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun ErrorDialog(
    onDismiss: () -> Unit
) {
    val activity = LocalContext.current as? Activity

    AlertDialog(
        onDismissRequest = {},
        title = { Text("Ошибка") },
        text = {
            Text("Возникла ошибка при получении списка рецептов.\nПожалуйста, перезайдите в приложение.")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    activity?.finishAffinity()
                    onDismiss()
                }
            ) {
                Text("ОК")
            }
        }
    )
}

fun diet(recipe: Recipe): String {
    val diets = mutableListOf<String>()

    if (recipe.isVegetarian) diets.add("vegetarian")
    if (recipe.isVegan) diets.add("vegan")
    if (recipe.isGlutenFree) diets.add("glutenFree")

    return diets.joinToString(", ")
}