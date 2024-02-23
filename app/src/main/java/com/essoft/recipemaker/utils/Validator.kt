package com.essoft.recipemaker.utils

import com.essoft.recipemaker.viewmodel.add.AddState

object Validator {
    fun isValidRecipe(uiState: AddState): Boolean {
        if (uiState.uri.isEmpty()) {
            return false
        }

        if (uiState.name.isEmpty()) {
            return false
        }

        if (uiState.type.isEmpty()) {
            return false
        }

        if (uiState.ingredient.isEmpty())
        {
            return false
        }

        if (uiState.instruction.isEmpty()) {
            return false
        }

        return true
    }
}