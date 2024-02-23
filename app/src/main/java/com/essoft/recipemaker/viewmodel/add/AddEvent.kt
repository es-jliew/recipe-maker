package com.essoft.recipemaker.viewmodel.add


sealed class AddEvent {
    data class UpdateName(val name: String) : AddEvent()
    data class UpdateType(val type: String) : AddEvent()
    data class UpdateUri(val uri: String) : AddEvent()
    data class UpdateIngredient(val ingredient: String) : AddEvent()
    data class UpdateInstruction(val instruction: String) : AddEvent()
    data class UpdateSelectedTab(val index: Int) : AddEvent()
    data class UpdateShowSnackbar(val show: Boolean): AddEvent()
}