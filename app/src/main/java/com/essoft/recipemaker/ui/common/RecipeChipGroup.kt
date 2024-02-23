package com.essoft.recipemaker.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.essoft.recipemaker.model.RecipeType

@Preview
@Composable
fun RecipeChipGroup(
    recipeTypes: List<RecipeType> = RecipeType.getAllRecipeTypes(),
    selectedRecipeType: RecipeType? = null,
    onSelectedChanged: (String) -> Unit = {}
) {
    Column {
        LazyRow {
            items(recipeTypes) { it ->
                RecipeChip(
                    name = it.name,
                    isSelected = selectedRecipeType == it,
                    onSelectionChanged = { onSelectedChanged(it) }
                )
            }
        }
    }
}