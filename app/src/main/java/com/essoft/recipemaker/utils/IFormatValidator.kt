package com.essoft.recipemaker.utils

import com.essoft.recipemaker.viewmodel.add.AddUiState

interface IFormatValidator {
    fun isValidRecipe(uiState: AddUiState): Boolean
}