package com.essoft.recipemaker.viewmodel.add

import com.essoft.recipemaker.model.DbRecipeModel

sealed class AddEvent {
    data class UpdateRecipeName(val name: String) : AddEvent()
    data class UpdateRecipeType(val type: String) : AddEvent()
    data class UpdateImageUri(val uri: String) : AddEvent()
    data class UpdateRecipeIngredient(val ingredient: String) : AddEvent()
    data class UpdateRecipeInstruction(val instruction: String) : AddEvent()
    data class UpdateSelectedTab(val index: Int) : AddEvent()
    data class ShowSnackbar(val show: Boolean): AddEvent()
    data class SetupUi(val dbRecipe: DbRecipeModel): AddEvent()
    data object SaveRecipe : AddEvent()
}