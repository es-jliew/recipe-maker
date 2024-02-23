package com.essoft.recipemaker.model

data class NonEntityRecipeModel(
    var id: Long = 0,
    var imageUri: String? = null,
    var name: String = "",
    var type: String? = null,
    var ingredients: String = "",
    var instructions: String = ""
)
