package com.essoft.recipemaker.viewmodel.detail

sealed class DetailEvent {
    data class UpdateSelectedTab(val index: Int) : DetailEvent()
    data class DeleteRecipe(val id: Long) : DetailEvent()
    data class UpdateShowSnackbar(val show: Boolean): DetailEvent()
}