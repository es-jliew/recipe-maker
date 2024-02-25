package com.essoft.recipemaker.viewmodel.home

import com.essoft.recipemaker.model.RecipeType

sealed class HomeEvent {
    data class FilterRecipe(val recipeType: RecipeType) : HomeEvent()
    data object SetupUi: HomeEvent()
}