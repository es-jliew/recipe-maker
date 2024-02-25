package com.essoft.recipemaker.viewmodel.detail

import com.essoft.recipemaker.model.DbRecipeModel

sealed class DetailEvent {
    data class DeleteRecipe(val id: Long): DetailEvent()
    data class UpdateSelectedTab(val index: Int): DetailEvent()
    data class ShowSnackbar(val show: Boolean): DetailEvent()
    data class SetupUi(val dbRecipe: DbRecipeModel): DetailEvent()
}