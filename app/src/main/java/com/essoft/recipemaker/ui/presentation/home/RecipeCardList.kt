package com.essoft.recipemaker.ui.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.essoft.recipemaker.model.DbRecipeModel

@Composable
fun RecipeCardList(
    recipes: List<DbRecipeModel>,
    onClick: (DbRecipeModel) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth().padding(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (recipes.isNotEmpty()) {
            items(recipes) { it ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { onClick(it) })
                ) {
                    RecipeCard(it)
                }
            }
        }
    }
}
